# Assignment 1 - Builder Pattern (Travel Package)

Java implementation of the **Builder** design pattern.
The product is a **Travel Package**, and the same construction steps produce **two
different representations**:

1. an immutable `TravelPackage` **object**, and
2. a printable **text itinerary** (`String`).

## How to run

Requires JDK 17 or newer (developed on JDK 21). No external dependencies.

```bash
javac -d out $(find src -name "*.java")
java -cp out com.example.travel.Main
```

In IntelliJ IDEA: open the project folder, make sure `src` is marked as the sources root
(right-click → Mark Directory as → Sources Root), then run `com.example.travel.Main`.

## Project structure

```
src/com/example/travel/
├── TravelPackage.java                # Product - immutable
├── Hotel.java                        # Immutable value object (name + star rating)
├── Activity.java                     # Immutable value object used by the product
├── MealPlan.java                     # Enum - board type
├── TransportType.java                # Enum - way of travelling
├── BuildStep.java                    # Enum - required construction steps
├── PackageLimits.java                # Single source of truth for business limits
├── Preconditions.java                # Reusable guard clauses
├── IncompletePackageException.java   # Clear failure when a step is missing
├── TravelPackageBuilder.java         # Builder interface (fluent)
├── TravelPackageObjectBuilder.java   # Concrete builder #1 -> TravelPackage
├── ItineraryBuilder.java             # Concrete builder #2 -> String itinerary
├── TravelAgencyDirector.java         # Director - 3 ready configurations
└── Main.java                         # Client
```

Everything lives in one package on purpose: it lets `TravelPackage` keep a
**package-private constructor**, so the product cannot be instantiated from outside
without a builder.

## How the requirements are covered

| Requirement | Where |
|---|---|
| Fluent API, every step returns the builder | all step methods in both builders `return this;` |
| Builder interface + two concrete builders | `TravelPackageBuilder`, `TravelPackageObjectBuilder`, `ItineraryBuilder` |
| Two different representations | `TravelPackage` object vs. text itinerary |
| Director with 2+ configurations | `TravelAgencyDirector.makeBeachHoliday / makeCityBreak / makeSkiTrip` |
| Director depends only on the interface | every director method takes `TravelPackageBuilder` |
| Immutable product | `final` fields, no setters, `List.copyOf`, package-private constructor |
| Validation in `getResult()` | missing steps -> `IncompletePackageException`; activity outside the stay -> `IllegalStateException` |
| No giant constructors | the product is configured step by step, never through a 8-argument public constructor |
| No magic numbers / strings | enums, `PackageLimits`, named format templates |

`getResult()` is **not** declared in the interface, because the two builders return
different types (`TravelPackage` and `String`). The director only calls the shared steps.

## Mapping to the pattern components (Lecture 1)

| Component | Class |
|---|---|
| Product | `TravelPackage` (representation 1), `String` itinerary (representation 2) |
| Builder | `TravelPackageBuilder` |
| ConcreteBuilder | `TravelPackageObjectBuilder`, `ItineraryBuilder` |
| Director | `TravelAgencyDirector` |
| Client | `Main` |

## Design notes

* **`reset()`** is part of the interface, so one builder instance can be reused for
  several packages; the director calls it before every configuration.
* **Covariant return types**: the concrete builders narrow the return type
  (`TravelPackageObjectBuilder` instead of `TravelPackageBuilder`), so client code can
  chain steps and call `getResult()` without a cast.
* **Value objects instead of long argument lists**: the hotel name and its star rating are
  always used and validated together, so they live in a `Hotel` object; the product
  constructor takes seven parameters instead of eight loose values.
* **Where validation happens**: arguments are checked immediately in each step
  ("fail fast"), while completeness is checked once in `getResult()`.
* The itinerary builder never creates a `TravelPackage`; it appends formatted lines to a
  `StringBuilder`. That is what makes it a real second representation and not a copy of
  the first builder.

## Sample output

See `sample-output.txt` for the full run. Beach holiday, itinerary representation:

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

Validation section of the same run:

```
Caught: Cannot build the travel package, missing step(s): hotel
Caught: hotel stars must be between 1 and 5, but was 9
Caught: Activity on day 10 does not fit a stay of 2 nights
```
