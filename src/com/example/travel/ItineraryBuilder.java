package com.example.travel;

import java.util.EnumSet;

public class ItineraryBuilder implements TravelPackageBuilder {
    private static final String LINE_END = System.lineSeparator();
    private static final String HEADER = "===== TRAVEL ITINERARY =====";
    private static final String ACTIVITIES_HEADER = "Programme:";
    private static final String FOOTER = "============================";

    private static final String DESTINATION_LINE = "Destination : %s";
    private static final String HOTEL_LINE = "Hotel       : %s";
    private static final String NIGHTS_LINE = "Duration    : %d night(s)";
    private static final String TRAVELLERS_LINE = "Travellers  : %d";
    private static final String TRANSPORT_LINE = "Transport   : %s";
    private static final String MEAL_PLAN_LINE = "Meal plan   : %s";
    private static final String ACTIVITY_LINE = "  day %d - %s";

    private final EnumSet<BuildStep> completedSteps = EnumSet.noneOf(BuildStep.class);
    private StringBuilder header = new StringBuilder();
    private StringBuilder programme = new StringBuilder();

    @Override
    public ItineraryBuilder reset() {
        completedSteps.clear();
        header = new StringBuilder();
        programme = new StringBuilder();
        return this;
    }

    @Override
    public ItineraryBuilder destination(String destination) {
        String safeDestination = Preconditions.requireText(destination, "destination");
        appendHeaderLine(DESTINATION_LINE, safeDestination);
        completedSteps.add(BuildStep.DESTINATION);
        return this;
    }

    @Override
    public ItineraryBuilder hotel(String name, int stars) {
        appendHeaderLine(HOTEL_LINE, Hotel.of(name, stars));
        completedSteps.add(BuildStep.HOTEL);
        return this;
    }

    @Override
    public ItineraryBuilder nights(int nights) {
        appendHeaderLine(NIGHTS_LINE,
                Preconditions.requireInRange(nights, PackageLimits.MIN_NIGHTS,
                        PackageLimits.MAX_NIGHTS, "nights"));
        completedSteps.add(BuildStep.NIGHTS);
        return this;
    }

    @Override
    public ItineraryBuilder travellers(int travellers) {
        appendHeaderLine(TRAVELLERS_LINE,
                Preconditions.requireInRange(travellers, PackageLimits.MIN_TRAVELLERS,
                        PackageLimits.MAX_TRAVELLERS, "travellers"));
        return this;
    }

    @Override
    public ItineraryBuilder transport(TransportType transport) {
        appendHeaderLine(TRANSPORT_LINE,
                Preconditions.requireNotNull(transport, "transport").getLabel());
        return this;
    }

    @Override
    public ItineraryBuilder mealPlan(MealPlan mealPlan) {
        appendHeaderLine(MEAL_PLAN_LINE,
                Preconditions.requireNotNull(mealPlan, "meal plan").getLabel());
        return this;
    }

    @Override
    public ItineraryBuilder addActivity(int day, String description) {
        Activity activity = Activity.of(day, description);
        programme.append(String.format(ACTIVITY_LINE, activity.getDay(), activity.getDescription()))
                .append(LINE_END);
        return this;
    }

    public String getResult() {
        EnumSet<BuildStep> missingSteps = EnumSet.complementOf(completedSteps);
        if (!missingSteps.isEmpty()) {
            throw new IncompletePackageException(missingSteps);
        }
        StringBuilder itinerary = new StringBuilder()
                .append(HEADER).append(LINE_END)
                .append(header);
        if (programme.length() > 0) {
            itinerary.append(ACTIVITIES_HEADER).append(LINE_END).append(programme);
        }
        return itinerary.append(FOOTER).append(LINE_END).toString();
    }

    private void appendHeaderLine(String template, Object... values) {
        header.append(String.format(template, values)).append(LINE_END);
    }
}
