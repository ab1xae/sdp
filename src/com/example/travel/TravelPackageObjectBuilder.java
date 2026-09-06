package com.example.travel;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class TravelPackageObjectBuilder implements TravelPackageBuilder {
    private static final TransportType DEFAULT_TRANSPORT = TransportType.FLIGHT;
    private static final MealPlan DEFAULT_MEAL_PLAN = MealPlan.BED_AND_BREAKFAST;

    private final EnumSet<BuildStep> completedSteps = EnumSet.noneOf(BuildStep.class);
    private List<Activity> activities = new ArrayList<>();

    private String destination;
    private Hotel hotel;
    private int nights;
    private int travellers = PackageLimits.DEFAULT_TRAVELLERS;
    private TransportType transport = DEFAULT_TRANSPORT;
    private MealPlan mealPlan = DEFAULT_MEAL_PLAN;

    @Override
    public TravelPackageObjectBuilder reset() {
        completedSteps.clear();
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
        completedSteps.add(BuildStep.DESTINATION);
        return this;
    }

    @Override
    public TravelPackageObjectBuilder hotel(String name, int stars) {
        this.hotel = Hotel.of(name, stars);
        completedSteps.add(BuildStep.HOTEL);
        return this;
    }

    @Override
    public TravelPackageObjectBuilder nights(int nights) {
        this.nights = Preconditions.requireInRange(nights, PackageLimits.MIN_NIGHTS,
                PackageLimits.MAX_NIGHTS, "nights");
        completedSteps.add(BuildStep.NIGHTS);
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
        activities.add(Activity.of(day, description));
        return this;
    }

    public TravelPackage getResult() {
        EnumSet<BuildStep> missingSteps = EnumSet.complementOf(completedSteps);
        if (!missingSteps.isEmpty()) {
            throw new IncompletePackageException(missingSteps);
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
