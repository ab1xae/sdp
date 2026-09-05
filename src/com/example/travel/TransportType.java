package com.example.travel;

public enum TransportType {
    FLIGHT("Flight"),
    TRAIN("Train"),
    COACH("Coach"),
    FERRY("Ferry");

    private final String label;

    TransportType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
