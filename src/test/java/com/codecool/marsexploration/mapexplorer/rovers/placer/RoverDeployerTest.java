package com.codecool.marsexploration.mapexplorer.rovers.placer;

import com.codecool.marsexploration.mapexplorer.calculators.service.CoordinateCalculator;
import com.codecool.marsexploration.mapexplorer.calculators.service.CoordinateCalculatorImpl;
import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;
import com.codecool.marsexploration.mapexplorer.maploader.model.MapModel;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RoverDeployerTest {
    String[][] fakeMapRepresentation = {
            {" ", "#", "&"},
            {" ", "*", "%"},
            {"#", " ", "#"}
    };

    MapModel fakeMap = new MapModel(fakeMapRepresentation, true);
    Coordinate spaceShip = new Coordinate(0,0);
    CoordinateCalculator coordinateCalculator = new CoordinateCalculatorImpl(fakeMap);

    @Test
    void getPlacement() {
        RoverDeployer roverDeployer = new RoverDeployer(fakeMap,coordinateCalculator);
        List<Coordinate> possibleCoordinateForRover = List.of(new Coordinate(0,1), new Coordinate(1,1),new Coordinate(1,0));
        Coordinate actual = roverDeployer.getPlacement(spaceShip);
        assertTrue(possibleCoordinateForRover.stream().anyMatch(coord -> coord.equals(actual)));
    }

    @Test
    void placeReturnTrue() {
        RoverDeployer roverDeployer = new RoverDeployer(fakeMap,coordinateCalculator);
        roverDeployer.place(new Coordinate(0,1));
        assertTrue(fakeMapRepresentation[0][1].equals("@"));
    }

    @Test
    void placeReturnFalse() {
        RoverDeployer roverDeployer = new RoverDeployer(fakeMap,coordinateCalculator);
        roverDeployer.place(new Coordinate(0,1));
        assertFalse(fakeMapRepresentation[0][1].equals(" "));
    }
}