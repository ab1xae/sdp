package com.example.travel;

public final class Activity {
    private final int day;
    private final String description;

    private Activity(int day, String description) {
        this.day = day;
        this.description = description;
    }

    public static Activity of(int day, String description) {
        return new Activity(
                Preconditions.requireInRange(day, PackageLimits.FIRST_DAY,
                        PackageLimits.MAX_DAY, "day"),
                Preconditions.requireText(description, "description"));
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
