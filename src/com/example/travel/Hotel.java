package com.example.travel;

public final class Hotel {
    private static final String STAR_SUFFIX = "*";

    private final String name;
    private final int stars;

    private Hotel(String name, int stars) {
        this.name = name;
        this.stars = stars;
    }

    public static Hotel of(String name, int stars) {
        return new Hotel(
                Preconditions.requireText(name, "hotel name"),
                Preconditions.requireInRange(stars, PackageLimits.MIN_STARS,
                        PackageLimits.MAX_STARS, "hotel stars"));
    }

    public String getName() {
        return name;
    }

    public int getStars() {
        return stars;
    }

    @Override
    public String toString() {
        return name + " (" + stars + STAR_SUFFIX + ")";
    }
}
