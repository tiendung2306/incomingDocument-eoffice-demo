package com.dux.cnweb.application.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dux.cnweb.application.commands.CreateDocumentCommand;
import com.dux.cnweb.application.commands.DistributeDocumentCommand;
import com.dux.cnweb.application.commands.TransferLeadCommand;
import com.dux.cnweb.domain.model.aggregates.Document;
import com.dux.cnweb.domain.model.entities.DocumentId;
import com.dux.cnweb.domain.model.valueobjects.AccessControlList;
import com.dux.cnweb.domain.model.valueobjects.RolePermissionMapper.Permission;
import com.dux.cnweb.domain.model.valueobjects.RolePermissionMapper.Role;
import com.dux.cnweb.domain.repositories.DocumentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class DocumentService {
    // Coordinate between multiple aggregates
    // Call external services
    // Perform business logic
    // Save the document using the repository
    // Return a response or result

    private final DocumentRepository documentRepository;
    // private final UserService userService; // External dependency

    public Document processDocument(CreateDocumentCommand command) {
        // Neu nguoi dung khong duoc thiet lap la van thu nhap so van ban den giay thi
        // throw 403

        DocumentId documentId = DocumentId.newId();

        Document document = Document.create(
                documentId,
                command.getDocumentType(),
                command.getNotebook(),
                command.getDocumentPriority(),
                command.getDocumentIdentifier(),
                command.getRecievedDate(),
                command.getDueDate(),
                command.getIssuedDate(),
                command.getIssuingAgency(),
                command.getSignatureInfo(),
                command.getDocumentSummary(),
                command.getDocumentNote(),
                command.getDocumentFiles());

        documentRepository.save(document);
        return document;
    }

    public Document processDocument(DistributeDocumentCommand command) {
        Document document = documentRepository.findById(command.getDocumentId())
                .orElseThrow(() -> new IllegalArgumentException("Document not found"));

        // Perform distribution logic here

        // Nếu đơn vị có văn thư hoặc có đơn vị văn thư quản lý và luồng văn bản đến
        // thiết lập văn thư nhập sổ văn bản thì văn bản cần được văn thư nhập sổ trước.

        // Người dùng được thiết lập quyền “Lãnh đạo nhận và phân phối văn bản” trong
        // chức năng Quản lý luồng văn bản đến của đơn vị mình.

        List<AccessControlList> acl = new ArrayList<>();
        acl.add(AccessControlList.of(command.getLeadUnitId(), Role.CHU_TRI));
        command.getCollaborativeUnitIds().forEach(unitId -> {
            acl.add(AccessControlList.of(unitId, Role.PHOI_HOP));
        });
        command.getInformeeUnitIds().forEach(unitId -> {
            acl.add(AccessControlList.of(unitId, Role.XEM_DE_BIET));
        });
        document.updateAccessControlList(acl);

        documentRepository.save(document);
        return document;
    }

    public Document proccessDocument(String userId, TransferLeadCommand command) {
        Document document = documentRepository.findById(command.getDocumentId())
                .orElseThrow(() -> new IllegalArgumentException("Document not found"));

        // Perform transfer lead logic here
        // Check if the user has permission to transfer lead
        Boolean isHavingPermission = false;
        for (AccessControlList acl : document.getAclList()) {
            if (acl.getUnitId().equals(userId) && acl.hasPermission(Permission.CHUYEN_CT)) {
                isHavingPermission = true;
                break;
            }
        }
        if (!isHavingPermission) {
            throw new IllegalArgumentException("User does not have permission to transfer lead");
        }

        // Update the lead unit
        document.updateAccessControlList(List.of(AccessControlList.of(command.getNewLeadUnitId(), Role.CHU_TRI)));
        return document;
    }
}
