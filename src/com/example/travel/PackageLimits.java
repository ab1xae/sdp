package com.example.travel;

final class PackageLimits {
    static final int MIN_STARS = 1;
    static final int MAX_STARS = 5;

    static final int MIN_NIGHTS = 1;
    static final int MAX_NIGHTS = 90;

    static final int MIN_TRAVELLERS = 1;
    static final int MAX_TRAVELLERS = 20;

    static final int FIRST_DAY = 1;
    static final int MAX_DAY = MAX_NIGHTS + 1;

    static final int DEFAULT_TRAVELLERS = 1;

    private PackageLimits() {
        throw new AssertionError("Constant holder must not be instantiated");
    }
}
