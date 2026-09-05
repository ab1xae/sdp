package com.example.travel;

import java.util.List;

public final class TravelPackage {
    private final String destination;
    private final Hotel hotel;
    private final int nights;
    private final int travellers;
    private final TransportType transport;
    private final MealPlan mealPlan;
    private final List<Activity> activities;

    TravelPackage(String destination,
                  Hotel hotel,
                  int nights,
                  int travellers,
                  TransportType transport,
                  MealPlan mealPlan,
                  List<Activity> activities) {
        this.destination = destination;
        this.hotel = hotel;
        this.nights = nights;
        this.travellers = travellers;
        this.transport = transport;
        this.mealPlan = mealPlan;
        this.activities = List.copyOf(activities);
    }

    public String getDestination() {
        return destination;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public int getNights() {
        return nights;
    }

    public int getTravellers() {
        return travellers;
    }

    public TransportType getTransport() {
        return transport;
    }

    public MealPlan getMealPlan() {
        return mealPlan;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    @Override
    public String toString() {
        return "TravelPackage[" + destination
                + ", " + hotel
                + ", " + nights + " nights"
                + ", " + travellers + " travellers"
                + ", " + transport.getLabel()
                + ", " + mealPlan.getLabel()
                + ", activities=" + activities.size() + "]";
    }
}
