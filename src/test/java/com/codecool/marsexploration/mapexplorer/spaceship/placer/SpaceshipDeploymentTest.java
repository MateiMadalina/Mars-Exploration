package com.codecool.marsexploration.mapexplorer.spaceship.placer;

import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;
import com.codecool.marsexploration.mapexplorer.maploader.model.MapModel;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import static org.junit.jupiter.api.Assertions.*;

class SpaceshipDeploymentTest {


    @Test
    void placeReturnTrue() {
        String[][] fakeMapRepresentation = {
                {" ", "#", "&"},
                {" ", "*", "%"},
                {"#", " ", "#"}
        };

        MapModel fakeMap = new MapModel(fakeMapRepresentation, true);
        SpaceshipDeployment spaceshipDeployment = new SpaceshipDeployment(fakeMap);
        Coordinate spaceShip = new Coordinate(0,0);
        spaceshipDeployment.place(spaceShip);
        assertEquals("$", fakeMapRepresentation[spaceShip.x()][spaceShip.y()]);
    }

    @Test
    void placeReturnFalse() {
        String[][] fakeMapRepresentation = {
                {" ", "#", "&"},
                {" ", "*", "%"},
                {"#", " ", "#"}
        };

        MapModel fakeMap = new MapModel(fakeMapRepresentation, true);
        SpaceshipDeployment spaceshipDeployment = new SpaceshipDeployment(fakeMap);
        Coordinate spaceShip = new Coordinate(0,0);
        spaceshipDeployment.place(spaceShip);
        assertNotEquals(" ", fakeMapRepresentation[spaceShip.x()][spaceShip.y()]);
    }
}