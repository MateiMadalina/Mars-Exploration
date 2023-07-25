package com.codecool.marsexploration.mapexplorer.spaceship.placer;

import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;
import com.codecool.marsexploration.mapexplorer.maploader.model.Map;

public class SpaceshipDeployment {
    private final Map map;

    public SpaceshipDeployment(Map map) {
        this.map = map;
    }

    public void place( Coordinate spaceShipCoordinate){
        String[][] mapRepresentation = map.getRepresentation();
        mapRepresentation[spaceShipCoordinate.x()][spaceShipCoordinate.y()] = "$";
    }
}
