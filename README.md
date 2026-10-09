# Parking Lot Management Model

An object-oriented Java model of a multi-storey parking facility. The project manages parking spaces, vehicles and entry-gate operations while taking vehicle size and preferred floors into account.

## Features

- Creates a parking facility with multiple floors and spaces
- Models small and large vehicles with different space requirements
- Searches for a suitable space on the driver's preferred floor
- Falls back to other floors when the preferred floor is full
- Registers vehicles and assigns ticket identifiers
- Releases occupied spaces when a vehicle leaves
- Produces a textual representation of the current parking layout
- Includes JUnit 5 tests for constructor validation and parking-layout output

## Project structure

```text
parking/
├── ParkingLot.java
├── ParkingLotTest.java
└── facility/
    ├── Gate.java
    └── Space.java

vehicle/
├── Car.java
└── Size.java
```

## Requirements

- A Java Development Kit
- JUnit 5 for the functional tests

## Compiling the domain classes

From the repository root:

```bash
javac -d out vehicle/Size.java vehicle/Car.java parking/facility/Space.java parking/ParkingLot.java parking/facility/Gate.java
```

The project currently focuses on the domain model and its tests, so it does not provide an interactive command-line interface.

## Testing

`parking/ParkingLotTest.java` covers invalid constructor arguments and the textual representation of a populated parking facility. Configure JUnit 5 in your IDE to run the test class.

## Project background

This project was developed as university coursework to practise Java class design, encapsulation, collections, two-dimensional data structures, exception handling and automated testing.
