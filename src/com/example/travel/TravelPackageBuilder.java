package com.example.travel;

public interface TravelPackageBuilder {
    TravelPackageBuilder reset();

    TravelPackageBuilder destination(String destination);

    TravelPackageBuilder hotel(String name, int stars);

    TravelPackageBuilder nights(int nights);

    TravelPackageBuilder travellers(int travellers);

    TravelPackageBuilder transport(TransportType transport);

    TravelPackageBuilder mealPlan(MealPlan mealPlan);

    TravelPackageBuilder addActivity(int day, String description);
}
