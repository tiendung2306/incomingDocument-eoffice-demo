package com.dux.cnweb.infrastructure.rest.controller;

import com.dux.cnweb.application.commands.CreatePaperDocumentCommand;
import com.dux.cnweb.application.commands.CreateDigitalDocumentCommand;
import com.dux.cnweb.application.IncomingApplicationService;
import com.dux.cnweb.domain.model.Document;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/incoming")
@CrossOrigin(origins = "*")
public class IncomingController {
    private final IncomingApplicationService incomingApplicationService;

    public IncomingController(IncomingApplicationService incomingApplicationService) {
        this.incomingApplicationService = incomingApplicationService;
        System.out.println("IncomingController initialized!");
    }

    @GetMapping("")
    public ResponseEntity<?> getAllDocuments() {
        return ResponseEntity.ok("Yo");
    }
    @PostMapping("/create/paper")
    public ResponseEntity<?> createPaperController(@RequestBody CreatePaperDocumentCommand command) {
        Document document = incomingApplicationService.createPaperDocument(command);
        return ResponseEntity.ok(document);
    }

    @PostMapping("/create/digital")
    public ResponseEntity<?> createDigitalController(@RequestBody CreateDigitalDocumentCommand command) { 
        Document document = incomingApplicationService.createDigitalDocument(command);
        return ResponseEntity.ok(document);
    }

    @PostMapping("/log/{id}")
    public ResponseEntity<?> logDocument(@PathVariable String id) {
        // Logging logic 
        System.out.println("Document logged: " + id);
        return ResponseEntity.ok("Document logged");
    }
}
