package com.example.travel;

public final class Activity {
    private final int day;
    private final String description;

    public Activity(int day, String description) {
        this.day = Preconditions.requireInRange(day, PackageLimits.FIRST_DAY,
                PackageLimits.MAX_DAY, "day");
        this.description = Preconditions.requireText(description, "description");
    }

    public int getDay() {
        return day;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Day " + day + ": " + description;
    }
}