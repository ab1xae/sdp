# Assignment 1 — Builder Pattern

Builder design pattern in Java. Product: **Travel Package**.

The same construction steps produce two different representations:

1. an immutable `TravelPackage` object
2. a printable text itinerary (`String`)

## Run

Requires JDK 17+. No dependencies.

```bash
javac -d out $(find src -name "*.java")
java -cp out com.example.travel.Main
```

## Classes

| Role | Class |
|---|---|
| Product | `TravelPackage` |
| Builder | `TravelPackageBuilder` |
| Concrete builders | `TravelPackageObjectBuilder`, `ItineraryBuilder` |
| Director | `TravelAgencyDirector` |
| Client | `Main` |

`Hotel`, `Activity`, `MealPlan`, `TransportType`, `BuildStep`, `PackageLimits`,
`Preconditions` and `IncompletePackageException` are supporting types.

## Output

```
===== TRAVEL ITINERARY =====
Destination : Antalya, Turkiye
Hotel       : Blue Lagoon Resort (5*)
Duration    : 7 night(s)
Travellers  : 2
Transport   : Flight
Meal plan   : All inclusive
Programme:
  day 3 - Boat trip along the coast
  day 5 - Spa and hammam afternoon
============================
```

Full run is in `sample-output.txt`.