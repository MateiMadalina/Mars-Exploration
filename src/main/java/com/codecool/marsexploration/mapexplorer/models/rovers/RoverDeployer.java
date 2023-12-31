package com.codecool.marsexploration.mapexplorer.models.rovers;

import com.codecool.marsexploration.mapexplorer.calculators.CoordinateCalculator;
import com.codecool.marsexploration.mapexplorer.map.Coordinate;
import com.codecool.marsexploration.mapexplorer.map.MapModel;

import java.util.List;
import java.util.Random;

public class RoverDeployer {

    private final MapModel map;
    private final CoordinateCalculator coordinateCalculator;
    private final Rover rover;


    public RoverDeployer(MapModel map, CoordinateCalculator coordinateCalculator, Rover rover) {
        this.map = map;
        this.coordinateCalculator = coordinateCalculator;
        this.rover = rover;
    }

    public Coordinate getPlacement(Coordinate spaceshipCoordinate){
        Random random = new Random();
        List<Coordinate> potentialDeploymentCoordinate = (List<Coordinate>) coordinateCalculator.getAdjacentCoordinates(spaceshipCoordinate,1);
        return potentialDeploymentCoordinate.get(random.nextInt(potentialDeploymentCoordinate.size()));
    }

    public void place(Coordinate roverCoordinate){
        String[][] mapRepresentation = map.representation();
        mapRepresentation[roverCoordinate.x()][roverCoordinate.y()] = "@";
        rover.setCurrentPosition(roverCoordinate);
    }
}
