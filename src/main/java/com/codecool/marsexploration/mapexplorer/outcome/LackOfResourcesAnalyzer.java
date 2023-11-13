package com.codecool.marsexploration.mapexplorer.outcome;

import com.codecool.marsexploration.mapexplorer.calculators.CoordinateCalculator;
import com.codecool.marsexploration.mapexplorer.logger.Logger;
import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;
import com.codecool.marsexploration.mapexplorer.simulation.Simulation;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class LackOfResourcesAnalyzer implements Analyzer {
    private final Simulation simulation;

    private final CoordinateCalculator coordinateCalculator;

    int expectedPocketsOfWaterNearSpaceship = 2;
    int searchDimension = 10;

    public LackOfResourcesAnalyzer(Simulation simulation, CoordinateCalculator coordinateCalculator, Logger fileLogger) {
        this.simulation = simulation;
        this.coordinateCalculator = coordinateCalculator;
    }

    @Override
    public boolean analyze() {
        int expectedMinerals = 3;
        int expectedWaterPockets = 2;
        boolean resourcesResult = checkForEnoughResourcesOnMap(expectedMinerals, expectedWaterPockets);
        boolean spaceshipResult = checkForEnoughWaterNearSpaceship(expectedPocketsOfWaterNearSpaceship, searchDimension);

        return resourcesResult && spaceshipResult;
    }

    private boolean checkForEnoughWaterNearSpaceship(int expectedPocketsOfWaterNearSpaceship, int searchDimension) {
        Coordinate spaceshipCoordinate = simulation.getSpaceShip();
        String[][] mapRepresentation = simulation.getMap().getRepresentation();
        List<Coordinate> adjacentResources = (List<Coordinate>) coordinateCalculator.getAdjacentCoordinates(spaceshipCoordinate, searchDimension);

        int pocketsOfWater = (int) adjacentResources.stream()
                .filter(resource -> mapRepresentation[resource.x()][resource.y()].equals("*"))
                .count();
        return pocketsOfWater >= expectedPocketsOfWaterNearSpaceship;
    }

    private boolean checkForEnoughResourcesOnMap(int minerals, int water) {
        Map<String, Set<Coordinate>> resourceLocations = simulation.getRovers().get(0).getResourceLocations();
        if(resourceLocations.get("%") != null && resourceLocations.get("*") != null) {
            return resourceLocations.get("%").size() >= minerals && resourceLocations.get("*").size() >= water;
        }
        return false;
    }
}
