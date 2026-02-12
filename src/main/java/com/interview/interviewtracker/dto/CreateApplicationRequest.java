package com.interview.interviewtracker.dto;

import jakarta.validation.constraints.NotBlank;

public class CreateApplicationRequest {

    @NotBlank
    private String companyName;

    @NotBlank
    private String role;

    private String notes;

    public String getCompanyName() {
        return companyName;
    }

    public String getRole() {
        return role;
    }

    public String getNotes() {
        return notes;
    }
}
