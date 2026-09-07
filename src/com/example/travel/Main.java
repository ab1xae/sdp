package com.example.travel;

public final class Main {
    private static final String SECTION = "----------------------------------------";

    private Main() {
    }

    public static void main(String[] args) {
        TravelAgencyDirector director = new TravelAgencyDirector();

        showBeachHoliday(director);
        showCityBreak(director);
        showValidationInAction();
    }

    private static void showBeachHoliday(TravelAgencyDirector director) {
        System.out.println(SECTION);
        System.out.println("Beach holiday");
        System.out.println(SECTION);

        TravelPackageObjectBuilder objectBuilder = new TravelPackageObjectBuilder();
        director.makeBeachHoliday(objectBuilder);
        TravelPackage beachPackage = objectBuilder.getResult();
        System.out.println(beachPackage);

        ItineraryBuilder itineraryBuilder = new ItineraryBuilder();
        director.makeBeachHoliday(itineraryBuilder);
        System.out.println(itineraryBuilder.getResult());
    }

    private static void showCityBreak(TravelAgencyDirector director) {
        System.out.println(SECTION);
        System.out.println("City break");
        System.out.println(SECTION);

        TravelPackageObjectBuilder objectBuilder = new TravelPackageObjectBuilder();
        director.makeCityBreak(objectBuilder);
        System.out.println(objectBuilder.getResult());

        ItineraryBuilder itineraryBuilder = new ItineraryBuilder();
        director.makeCityBreak(itineraryBuilder);
        System.out.println(itineraryBuilder.getResult());
    }

    private static void showValidationInAction() {
        System.out.println(SECTION);
        System.out.println("Validation");
        System.out.println(SECTION);

        reportFailure(Main::buildPackageWithoutHotel);
        reportFailure(Main::buildItineraryWithImpossibleRating);
        reportFailure(Main::buildPackageWithActivityOutsideTheStay);
    }

    private static void buildPackageWithoutHotel() {
        new TravelPackageObjectBuilder()
                .destination("Almaty, Kazakhstan")
                .nights(2)
                .getResult();
    }

    private static void buildItineraryWithImpossibleRating() {
        new ItineraryBuilder().hotel("Grand Hotel", 9);
    }

    private static void buildPackageWithActivityOutsideTheStay() {
        new TravelPackageObjectBuilder()
                .destination("Astana, Kazakhstan")
                .hotel("Expo Hotel", 4)
                .nights(2)
                .addActivity(10, "Day trip to Borovoe")
                .getResult();
    }

    private static void reportFailure(Runnable invalidConstruction) {
        try {
            invalidConstruction.run();
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Caught: " + e.getMessage());
        }
    }
}
