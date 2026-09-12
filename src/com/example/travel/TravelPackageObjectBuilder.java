package com.example.travel;

import java.util.ArrayList;
import java.util.List;

public class TravelPackageObjectBuilder implements TravelPackageBuilder {
    private static final TransportType DEFAULT_TRANSPORT = TransportType.FLIGHT;
    private static final MealPlan DEFAULT_MEAL_PLAN = MealPlan.BED_AND_BREAKFAST;

    private List<Activity> activities = new ArrayList<>();

    private String destination;
    private Hotel hotel;
    private int nights;
    private int travellers = PackageLimits.DEFAULT_TRAVELLERS;
    private TransportType transport = DEFAULT_TRANSPORT;
    private MealPlan mealPlan = DEFAULT_MEAL_PLAN;

    @Override
    public TravelPackageObjectBuilder reset() {
        activities = new ArrayList<>();
        destination = null;
        hotel = null;
        nights = 0;
        travellers = PackageLimits.DEFAULT_TRAVELLERS;
        transport = DEFAULT_TRANSPORT;
        mealPlan = DEFAULT_MEAL_PLAN;
        return this;
    }

    @Override
    public TravelPackageObjectBuilder destination(String destination) {
        this.destination = Preconditions.requireText(destination, "destination");
        return this;
    }

    @Override
    public TravelPackageObjectBuilder hotel(String name, int stars) {
        this.hotel = new Hotel(name, stars);
        return this;
    }

    @Override
    public TravelPackageObjectBuilder nights(int nights) {
        this.nights = Preconditions.requireInRange(nights, PackageLimits.MIN_NIGHTS,
                PackageLimits.MAX_NIGHTS, "nights");
        return this;
    }

    @Override
    public TravelPackageObjectBuilder travellers(int travellers) {
        this.travellers =
                Preconditions.requireInRange(travellers, PackageLimits.MIN_TRAVELLERS,
                        PackageLimits.MAX_TRAVELLERS, "travellers");
        return this;
    }

    @Override
    public TravelPackageObjectBuilder transport(TransportType transport) {
        this.transport = Preconditions.requireNotNull(transport, "transport");
        return this;
    }

    @Override
    public TravelPackageObjectBuilder mealPlan(MealPlan mealPlan) {
        this.mealPlan = Preconditions.requireNotNull(mealPlan, "meal plan");
        return this;
    }

    @Override
    public TravelPackageObjectBuilder addActivity(int day, String description) {
        activities.add(new Activity(day, description));
        return this;
    }

    public TravelPackage getResult() {
        if (destination == null) {
            throw new IllegalStateException("Cannot build the package: destination is missing");
        }
        if (hotel == null) {
            throw new IllegalStateException("Cannot build the package: hotel is missing");
        }
        if (nights == 0) {
            throw new IllegalStateException("Cannot build the package: nights is missing");
        }
        verifyActivitiesFitTheStay();
        return new TravelPackage(destination, hotel, nights, travellers,
                transport, mealPlan, activities);
    }

    private void verifyActivitiesFitTheStay() {
        int lastDay = nights + 1;
        for (Activity activity : activities) {
            if (activity.getDay() > lastDay) {
                throw new IllegalStateException("Activity on day " + activity.getDay()
                        + " does not fit a stay of " + nights + " nights");
            }
        }
    }
}