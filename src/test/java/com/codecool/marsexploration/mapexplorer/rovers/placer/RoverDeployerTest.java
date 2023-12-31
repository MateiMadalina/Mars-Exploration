package com.codecool.marsexploration.mapexplorer.rovers.placer;

import com.codecool.marsexploration.mapexplorer.calculators.CoordinateCalculator;
import com.codecool.marsexploration.mapexplorer.calculators.CoordinateCalculatorImpl;
import com.codecool.marsexploration.mapexplorer.map.Coordinate;
import com.codecool.marsexploration.mapexplorer.map.MapModel;
import com.codecool.marsexploration.mapexplorer.models.rovers.Rover;
import com.codecool.marsexploration.mapexplorer.models.rovers.RoverDeployer;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class RoverDeployerTest {
    String[][] fakeMapRepresentation = {
            {" ", "#", "&"},
            {" ", "*", "%"},
            {"#", " ", "#"}
    };

    MapModel fakeMap = new MapModel(fakeMapRepresentation);
    Rover rover = mock(Rover.class);
    Coordinate spaceShip = new Coordinate(0,0);
    CoordinateCalculator coordinateCalculator = new CoordinateCalculatorImpl(fakeMap);

    @Test
    void getPlacement() {
        RoverDeployer roverDeployer = new RoverDeployer(fakeMap,coordinateCalculator, rover);
        List<Coordinate> possibleCoordinateForRover = List.of(new Coordinate(0,1), new Coordinate(1,1),new Coordinate(1,0));
        Coordinate actual = roverDeployer.getPlacement(spaceShip);
        assertTrue(possibleCoordinateForRover.stream().anyMatch(coord -> coord.equals(actual)));
    }

    @Test
    void placeReturnTrue() {
        RoverDeployer roverDeployer = new RoverDeployer(fakeMap,coordinateCalculator, rover);
        roverDeployer.place(new Coordinate(0,1));
        assertEquals("@", fakeMapRepresentation[0][1]);
    }

    @Test
    void placeReturnFalse() {
        RoverDeployer roverDeployer = new RoverDeployer(fakeMap,coordinateCalculator, rover);
        roverDeployer.place(new Coordinate(0,1));
        assertNotEquals(" ", fakeMapRepresentation[0][1]);
    }
}