package com.example.travel;

public final class Hotel {
    private final String name;
    private final int stars;

    public Hotel(String name, int stars) {
        this.name = Preconditions.requireText(name, "hotel name");
        this.stars = Preconditions.requireInRange(stars, PackageLimits.MIN_STARS,
                PackageLimits.MAX_STARS, "hotel stars");
    }

    public String getName() {
        return name;
    }

    public int getStars() {
        return stars;
    }

    @Override
    public String toString() {
        return name + " (" + stars + "*)";
    }
}