package com.codecool.marsexploration.mapexplorer.rovers;

import com.codecool.marsexploration.mapexplorer.calculators.service.CoordinateCalculator;
import com.codecool.marsexploration.mapexplorer.calculators.service.CoordinateCalculatorImpl;
import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;
import com.codecool.marsexploration.mapexplorer.maploader.model.MapModel;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class RoverTest {
    private final List<String> symbols = List.of("#", "%", "*", "&");
    private final String[][] fakeRepresentation =
            {
                    {" ", "#", "#", "#", " "},
                    {"#", "*", "$", " ", "%"},
                    {" ", "#", " ", "*", "%"},
                    {"*", "#", "#", " ", " "},
                    {" ", "#", "&", "&", " "}

            };

    @Test
    void addToResourceMap() throws FileNotFoundException {
        MapModel mapModel = new MapModel(fakeRepresentation, true);
        CoordinateCalculator calculator = new CoordinateCalculatorImpl(mapModel);
        Map<String, Set<Coordinate>> resourcesLocations = new HashMap<>();
        Coordinate currentPosition = new Coordinate(2, 2);
        Rover rover = new Rover(0, "rover", currentPosition, 2, resourcesLocations, calculator);

        Map<String, Set<Coordinate>> expected = Map.of(
                "#", Set.of(
                        new Coordinate(0, 1),
                        new Coordinate(0, 2),
                        new Coordinate(0, 3),
                        new Coordinate(1, 0),
                        new Coordinate(2, 1),
                        new Coordinate(3, 1),
                        new Coordinate(3, 2),
                        new Coordinate(4, 1)
                ),
                "%", Set.of(
                        new Coordinate(1, 4),
                        new Coordinate(2, 4)
                ),
                "&", Set.of(
                        new Coordinate(4,2),
                        new Coordinate(4,3)
                ),
                "*", Set.of(
                        new Coordinate(1,1),
                        new Coordinate(2,3),
                        new Coordinate(3,0)
                )
        );

        rover.addToResourceMap(mapModel, symbols);
        assertEquals(expected, resourcesLocations);
    }

    @Test
    void pickStep(){
        MapModel mapModel = new MapModel(fakeRepresentation, true);
        CoordinateCalculator calculator = new CoordinateCalculatorImpl(mapModel);
        Map<String, Set<Coordinate>> resourcesLocations = new HashMap<>();
        Coordinate currentPosition = new Coordinate(2, 2);
        Rover rover = new Rover(0, "rover", currentPosition, 2, resourcesLocations, calculator);

        List<Coordinate> expected = List.of(
                new Coordinate(1,3),
                new Coordinate(3,3)
        );


        Coordinate actual = rover.pickStep();
        System.out.println(actual);
        assertTrue(expected.stream().anyMatch(coordinate -> coordinate.equals(actual)));
    }

    @Test
    void returnToSpaceship(){
        MapModel mapModel = new MapModel(fakeRepresentation, true);
        CoordinateCalculator calculator = new CoordinateCalculatorImpl(mapModel);
        Map<String, Set<Coordinate>> resourcesLocations = new HashMap<>();
        Coordinate currentPosition = new Coordinate(2, 2);
        Rover rover = new Rover(0, "rover", currentPosition, 2, resourcesLocations, calculator);


        Coordinate expected = new Coordinate(1,2);
        rover.returnToSpaceship(expected);
        assertEquals(expected, rover.getCurrentPosition());
    }
}