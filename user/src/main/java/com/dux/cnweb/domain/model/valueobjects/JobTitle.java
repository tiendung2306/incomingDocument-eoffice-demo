package com.dux.cnweb.domain.model.valueobjects;

import lombok.Value;
import java.util.Arrays;
import java.util.Optional;

@Value
public class JobTitle {
    JobTitles jobTitle;

    public enum JobTitles {
        DOCUMENT_OFFICER("Document Officer"),
        ACCOUNTANT("Accountant"),
        ADMINISTRATIVE_OFFICER("Administrative Officer"),
        SECRETARY("Secretary"),
        IT_SPECIALIST("IT Specialist"),
        DIRECTOR("Director"),
        VICE_DIRECTOR("Vice Director");

        private final String displayName;

        JobTitles(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }

        // Tìm enum từ display name
        public static Optional<JobTitles> fromDisplayName(String displayName) {
            if (displayName == null) return Optional.empty();
            
            return Arrays.stream(values())
                    .filter(title -> title.displayName.equals(displayName.trim()))
                    .findFirst();
        }

        // Lấy tất cả valid titles để hiển thị error
        public static String getAllValidTitles() {
            return Arrays.stream(values())
                    .map(JobTitles::getDisplayName)
                    .reduce((a, b) -> a + ", " + b)
                    .orElse("");
        }
    }

    private JobTitle(JobTitles jobTitle) {
        if (jobTitle == null) {
            throw new IllegalArgumentException("Job title cannot be null");
        }
        this.jobTitle = jobTitle;
    }

    public static JobTitle of(String jobTitle) {
        if (jobTitle == null || jobTitle.trim().isEmpty()) {
            throw new IllegalArgumentException("Job title cannot be null or empty");
        }

        return JobTitles.fromDisplayName(jobTitle)
                .map(JobTitle::new)
                .orElseThrow(() -> new IllegalArgumentException(
                    "Invalid job title: " + jobTitle + 
                    ". Valid titles are: " + JobTitles.getAllValidTitles()));
    }

    public static JobTitle of(JobTitles jobTitle) {
        return new JobTitle(jobTitle);
    }

    public String getDisplayName() {
        return jobTitle.getDisplayName();
    }

    @Override
    public String toString() {
        return jobTitle.getDisplayName();
    }
}