package com.codecool.marsexploration.mapexplorer.spaceship.placer;

import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;
import com.codecool.marsexploration.mapexplorer.maploader.model.MapModel;

public class SpaceshipDeployment {
    private final MapModel map;

    public SpaceshipDeployment(MapModel map) {
        this.map = map;
    }

    public void place(Coordinate spaceShipCoordinate){
        String[][] mapRepresentation = map.representation();
        mapRepresentation[spaceShipCoordinate.x()][spaceShipCoordinate.y()] = "$";
    }
}
