package com.linkare.contact;

public enum MaritalStatus {
    SINGLE("Single"), MARRIED("Married"), DIVORCED("Divorced"), WIDOWER("Widower");

    private final String label;

    MaritalStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}