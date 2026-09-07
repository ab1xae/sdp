package com.example.travel;

public class TravelAgencyDirector {
    private static final String BEACH_DESTINATION = "Antalya, Turkiye";
    private static final String BEACH_HOTEL = "Blue Lagoon Resort";
    private static final int BEACH_HOTEL_STARS = 5;
    private static final int BEACH_NIGHTS = 7;
    private static final int BEACH_TRAVELLERS = 2;
    private static final String BEACH_ACTIVITY_BOAT = "Boat trip along the coast";
    private static final int BEACH_ACTIVITY_BOAT_DAY = 3;
    private static final String BEACH_ACTIVITY_SPA = "Spa and hammam afternoon";
    private static final int BEACH_ACTIVITY_SPA_DAY = 5;

    private static final String CITY_DESTINATION = "Prague, Czechia";
    private static final String CITY_HOTEL = "Old Town Boutique Hotel";
    private static final int CITY_HOTEL_STARS = 4;
    private static final int CITY_NIGHTS = 3;
    private static final int CITY_TRAVELLERS = 1;
    private static final String CITY_ACTIVITY_WALK = "Guided walking tour of the Old Town";
    private static final int CITY_ACTIVITY_WALK_DAY = 1;
    private static final String CITY_ACTIVITY_CASTLE = "Prague Castle and Charles Bridge";
    private static final int CITY_ACTIVITY_CASTLE_DAY = 2;

    private static final String SKI_DESTINATION = "Shymbulak, Kazakhstan";
    private static final String SKI_HOTEL = "Mountain View Lodge";
    private static final int SKI_HOTEL_STARS = 4;
    private static final int SKI_NIGHTS = 5;
    private static final int SKI_TRAVELLERS = 4;
    private static final String SKI_ACTIVITY_LESSON = "Beginner ski lesson";
    private static final int SKI_ACTIVITY_LESSON_DAY = 1;
    private static final String SKI_ACTIVITY_SUMMIT = "Cable car to the summit";
    private static final int SKI_ACTIVITY_SUMMIT_DAY = 4;

    public void makeBeachHoliday(TravelPackageBuilder builder) {
        builder.reset()
                .destination(BEACH_DESTINATION)
                .hotel(BEACH_HOTEL, BEACH_HOTEL_STARS)
                .nights(BEACH_NIGHTS)
                .travellers(BEACH_TRAVELLERS)
                .transport(TransportType.FLIGHT)
                .mealPlan(MealPlan.ALL_INCLUSIVE)
                .addActivity(BEACH_ACTIVITY_BOAT_DAY, BEACH_ACTIVITY_BOAT)
                .addActivity(BEACH_ACTIVITY_SPA_DAY, BEACH_ACTIVITY_SPA);
    }

    public void makeCityBreak(TravelPackageBuilder builder) {
        builder.reset()
                .destination(CITY_DESTINATION)
                .hotel(CITY_HOTEL, CITY_HOTEL_STARS)
                .nights(CITY_NIGHTS)
                .travellers(CITY_TRAVELLERS)
                .transport(TransportType.TRAIN)
                .mealPlan(MealPlan.BED_AND_BREAKFAST)
                .addActivity(CITY_ACTIVITY_WALK_DAY, CITY_ACTIVITY_WALK)
                .addActivity(CITY_ACTIVITY_CASTLE_DAY, CITY_ACTIVITY_CASTLE);
    }

    public void makeSkiTrip(TravelPackageBuilder builder) {
        builder.reset()
                .destination(SKI_DESTINATION)
                .hotel(SKI_HOTEL, SKI_HOTEL_STARS)
                .nights(SKI_NIGHTS)
                .travellers(SKI_TRAVELLERS)
                .transport(TransportType.COACH)
                .mealPlan(MealPlan.HALF_BOARD)
                .addActivity(SKI_ACTIVITY_LESSON_DAY, SKI_ACTIVITY_LESSON)
                .addActivity(SKI_ACTIVITY_SUMMIT_DAY, SKI_ACTIVITY_SUMMIT);
    }
}
