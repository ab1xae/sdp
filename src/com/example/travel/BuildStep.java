package com.example.travel;

public enum BuildStep {
    DESTINATION("destination"),
    HOTEL("hotel"),
    NIGHTS("nights");

    private final String label;

    BuildStep(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
