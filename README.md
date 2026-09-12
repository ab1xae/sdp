# Builder Pattern

Builder design pattern in Java. Product: Travel Package

The same construction steps produce two different results:

1. an immutable `TravelPackage` object
2. a text itinerary (`String`)


## Classes

| Role | Class |
|---|---|
| Product | `TravelPackage` |
| Builder | `TravelPackageBuilder` |
| Concrete builders | `TravelPackageObjectBuilder`, `ItineraryBuilder` |
| Director | `TravelAgencyDirector` |
| Client | `Main` |

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