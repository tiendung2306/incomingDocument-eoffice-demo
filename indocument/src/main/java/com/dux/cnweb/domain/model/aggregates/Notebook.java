package com.dux.cnweb.domain.model.aggregates;

import com.dux.cnweb.shared.domain.model.AggregateRoot;
import com.dux.cnweb.domain.model.entities.NotebookId;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
@Getter
public class Notebook extends AggregateRoot {
    private NotebookId id;
    private String name;
    private String notebookType; // So van ban den hoac so van ban di
    private String[] documentTypes; // Danh sach cac loai van ban co the co trong so
    private int minNumberAvailable;
    private int maxNumberAvailable;
    private Map<Integer, Boolean> usedNumbers;

    public static Notebook create(String name, int minNumber, int maxNumber, String notebookType,
            String[] documentTypes) {
        Notebook notebook = new Notebook();
        notebook.id = NotebookId.newId();
        notebook.name = name;
        notebook.notebookType = notebookType;
        notebook.documentTypes = documentTypes;
        notebook.minNumberAvailable = minNumber;
        notebook.maxNumberAvailable = maxNumber;
        notebook.usedNumbers = new HashMap<>();

        // Add domain event for notebook creation
        // notebook.addDomainEvent(new NotebookCreatedEvent(notebook.id.getValue(),
        // name));

        return notebook;
    }

    // Get the minimum available number in this notebook
    public int getMinAvailableNumber() {
        for (int i = minNumberAvailable; i <= maxNumberAvailable; i++) {
            if (!isNumberUsed(i)) {
                return i;
            }
        }
        return -1; // No available numbers
    }

    // Check if a specific number is available
    public boolean isNumberAvailable(int number) {
        if (number < minNumberAvailable || number > maxNumberAvailable) {
            return false;
        }
        return !isNumberUsed(number);
    }

    // Mark a number as used
    public void markNumberAsUsed(int number) {
        if (number >= minNumberAvailable && number <= maxNumberAvailable) {
            usedNumbers.put(number, true);
            // Optional: Add domain event for number allocation
            // this.addDomainEvent(new NotebookNumberAllocatedEvent(this.id.getValue(),
            // number));
        } else {
            throw new IllegalArgumentException("Number out of range for this notebook");
        }
    }

    // Release a number (mark as unused)
    public void releaseNumber(int number) {
        usedNumbers.remove(number);
        // Optional: Add domain event for number release
        // this.addDomainEvent(new NotebookNumberReleasedEvent(this.id.getValue(),
        // number));
    }

    private boolean isNumberUsed(int number) {
        return usedNumbers.containsKey(number) && usedNumbers.get(number);
    }

    // Constructor for reconstruction from database
    public Notebook(NotebookId id, String name, int minNumberAvailable,
            int maxNumberAvailable, Map<Integer, Boolean> usedNumbers) {
        this.id = id;
        this.name = name;
        this.minNumberAvailable = minNumberAvailable;
        this.maxNumberAvailable = maxNumberAvailable;
        this.usedNumbers = usedNumbers;
    }
}