package com.example.travel;

public enum MealPlan {
    ROOM_ONLY("Room only"),
    BED_AND_BREAKFAST("Bed & breakfast"),
    HALF_BOARD("Half board"),
    FULL_BOARD("Full board"),
    ALL_INCLUSIVE("All inclusive");

    private final String label;

    MealPlan(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
