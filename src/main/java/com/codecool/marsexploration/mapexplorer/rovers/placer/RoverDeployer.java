package com.codecool.marsexploration.mapexplorer.rovers.placer;

import com.codecool.marsexploration.mapexplorer.calculators.service.CoordinateCalculator;
import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;
import com.codecool.marsexploration.mapexplorer.maploader.model.MapModel;

import java.util.List;
import java.util.Random;

public class RoverDeployer {

    private final MapModel map;
    private final CoordinateCalculator coordinateCalculator;


    public RoverDeployer(MapModel map, CoordinateCalculator coordinateCalculator) {
        this.map = map;
        this.coordinateCalculator = coordinateCalculator;
    }

    public Coordinate getPlacement(Coordinate spaceshipCoordinate){
        Random random = new Random();
        List<Coordinate> potentialDeploymentCoordinate = (List<Coordinate>) coordinateCalculator.getAdjacentCoordinates(spaceshipCoordinate,1);
        Coordinate choosenDeplymentCoordinate = potentialDeploymentCoordinate.get(random.nextInt(potentialDeploymentCoordinate.size()));
        return choosenDeplymentCoordinate;
    }

    public void place(Coordinate roverCoordinate){
        String[][] mapRepresentation = map.getRepresentation();
        mapRepresentation[roverCoordinate.x()][roverCoordinate.y()] = "@";
    }
}
