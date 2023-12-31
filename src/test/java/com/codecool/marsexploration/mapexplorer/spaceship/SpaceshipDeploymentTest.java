package com.codecool.marsexploration.mapexplorer.spaceship;

import com.codecool.marsexploration.mapexplorer.map.Coordinate;
import com.codecool.marsexploration.mapexplorer.map.MapModel;
import com.codecool.marsexploration.mapexplorer.models.spaceship.SpaceshipDeployer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpaceshipDeploymentTest {


    @Test
    void placeReturnTrue() {
        String[][] fakeMapRepresentation = {
                {" ", "#", "&"},
                {" ", "*", "%"},
                {"#", " ", "#"}
        };

        MapModel fakeMap = new MapModel(fakeMapRepresentation);
        SpaceshipDeployer spaceshipDeployer = new SpaceshipDeployer(fakeMap);
        Coordinate spaceShip = new Coordinate(0,0);
        spaceshipDeployer.place(spaceShip);
        assertEquals("$", fakeMapRepresentation[spaceShip.x()][spaceShip.y()]);
    }

    @Test
    void placeReturnFalse() {
        String[][] fakeMapRepresentation = {
                {" ", "#", "&"},
                {" ", "*", "%"},
                {"#", " ", "#"}
        };

        MapModel fakeMap = new MapModel(fakeMapRepresentation);
        SpaceshipDeployer spaceshipDeployer = new SpaceshipDeployer(fakeMap);
        Coordinate spaceShip = new Coordinate(0,0);
        spaceshipDeployer.place(spaceShip);
        assertNotEquals(" ", fakeMapRepresentation[spaceShip.x()][spaceShip.y()]);
    }
}