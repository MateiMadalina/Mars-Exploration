package com.codecool.marsexploration.mapexplorer.rovers;

import com.codecool.marsexploration.mapexplorer.service.calculators.CoordinateCalculator;
import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;
import com.codecool.marsexploration.mapexplorer.maploader.model.MapModel;

import java.util.*;

public class Rover {
    private final String name;
    private Coordinate currentPosition;
    private final int sight;
    private Map<String, Set<Coordinate>> resourceLocations;
    private final CoordinateCalculator calculator;
    private final Random rand;
    private List<Coordinate> pickedSteps;
    private static int count = 1;

    public Rover(Coordinate currentPosition, int sight, Map<String, Set<Coordinate>> resourceLocations, CoordinateCalculator calculator) {
        int id = count;
        this.name = "Rover" + count;
        this.currentPosition = currentPosition;
        this.sight = sight;
        this.resourceLocations = resourceLocations;
        this.calculator = calculator;
        this.rand = new Random();
        this.pickedSteps = new ArrayList<>();
        count++;
    }

    public Coordinate pickStep() {
        List<Coordinate> adjacentCoordinates = (List<Coordinate>) calculator.getAdjacentCoordinates(this.currentPosition, 1);
        List<Coordinate> emptyAdjacentCoordinates = calculator.gatAllPossiblePlacementsForRoverWithEmptySpaceAdjacent(adjacentCoordinates);

        return emptyAdjacentCoordinates.get(rand.nextInt(emptyAdjacentCoordinates.size()));
    }

    public void moveToDestination(Coordinate destination) {
        this.currentPosition = destination;
    }

    public void addToResourceMap(MapModel mapModel, List<String> symbols) {
        String[][] mapRepresentation = mapModel.getRepresentation();
        List<Coordinate> coordinatesAroundRoverWithSight = (List<Coordinate>) calculator.getAdjacentCoordinates(this.currentPosition, this.sight);
        for (String symbol : symbols) {
            for (Coordinate coord : coordinatesAroundRoverWithSight) {
                if (mapRepresentation[coord.x()][coord.y()].equals(symbol)) {
                    Set<Coordinate> resourcesFound = this.resourceLocations.getOrDefault(symbol, new HashSet<>());
                    resourcesFound.add(coord);
                    this.resourceLocations.put(symbol, resourcesFound);
                }
            }
        }
    }

    public void addToPickedSteps(Coordinate coordinate) {
        this.pickedSteps.add(coordinate);
    }

    public void setCurrentPosition(Coordinate currentPosition) {
        this.currentPosition = currentPosition;
    }

    public Coordinate getCurrentPosition() {
        return currentPosition;
    }

    public List<Coordinate> getPickedSteps() {
        return pickedSteps;
    }

    public Map<String, Set<Coordinate>> getResourceLocations() {
        return resourceLocations;
    }

    public String getName() {
        return name;
    }
}
