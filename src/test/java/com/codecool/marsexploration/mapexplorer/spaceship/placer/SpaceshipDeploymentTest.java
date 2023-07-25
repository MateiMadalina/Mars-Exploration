package com.codecool.marsexploration.mapexplorer.spaceship.placer;

import com.codecool.marsexploration.mapexplorer.calculators.service.CoordinateCalculator;
import com.codecool.marsexploration.mapexplorer.calculators.service.CoordinateCalculatorImpl;
import com.codecool.marsexploration.mapexplorer.maploader.MapLoader;
import com.codecool.marsexploration.mapexplorer.maploader.MapLoaderImpl;
import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;
import com.codecool.marsexploration.mapexplorer.maploader.model.Map;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SpaceshipDeploymentTest {


    @Test
    void placeReturnTrue() throws FileNotFoundException {
        String[][] fakeMapRepresentation = {
                {" ", "#", "&"},
                {" ", "*", "%"},
                {"#", " ", "#"}
        };

        Map fakeMap = new Map(fakeMapRepresentation, true);
        SpaceshipDeployment spaceshipDeployment = new SpaceshipDeployment(fakeMap);
        Coordinate spaceShip = new Coordinate(0,0);
        spaceshipDeployment.place(spaceShip);
        assertTrue(fakeMapRepresentation[spaceShip.x()][spaceShip.y()].equals("$"));
    }

    @Test
    void placeReturnFalse() throws FileNotFoundException {
        String[][] fakeMapRepresentation = {
                {" ", "#", "&"},
                {" ", "*", "%"},
                {"#", " ", "#"}
        };

        Map fakeMap = new Map(fakeMapRepresentation, true);
        SpaceshipDeployment spaceshipDeployment = new SpaceshipDeployment(fakeMap);
        Coordinate spaceShip = new Coordinate(0,0);
        spaceshipDeployment.place(spaceShip);
        assertFalse(fakeMapRepresentation[spaceShip.x()][spaceShip.y()].equals(" "));
    }
}