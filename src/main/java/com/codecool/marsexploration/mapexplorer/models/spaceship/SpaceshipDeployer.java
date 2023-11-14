package com.codecool.marsexploration.mapexplorer.models.spaceship;

import com.codecool.marsexploration.mapexplorer.map.Coordinate;
import com.codecool.marsexploration.mapexplorer.map.MapModel;

public class SpaceshipDeployer {
    private final MapModel map;

    public SpaceshipDeployer(MapModel map) {
        this.map = map;
    }

    public void place(Coordinate spaceShipCoordinate){
        String[][] mapRepresentation = map.representation();
        mapRepresentation[spaceShipCoordinate.x()][spaceShipCoordinate.y()] = "$";
    }
}
