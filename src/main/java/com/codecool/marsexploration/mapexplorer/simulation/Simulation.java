package com.codecool.marsexploration.mapexplorer.simulation;

import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;
import com.codecool.marsexploration.mapexplorer.maploader.model.MapModel;
import com.codecool.marsexploration.mapexplorer.rovers.Rover;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Simulation {
    private int numberOfSteps;
    private final int stepsToTimeout;
    private final Rover rover;
    private final Coordinate spaceShip;
    private final MapModel map;
    private final List<String> symbols;

    public Simulation(int stepsToTimeout, Rover rover, Coordinate spaceShip, MapModel map, List<String> symbols) {
        this.numberOfSteps = 0;
        this.stepsToTimeout = stepsToTimeout;
        this.rover = rover;
        this.spaceShip = spaceShip;
        this.map = map;
        this.symbols = symbols;
    }

    public int getStepsToTimeout() {
        return stepsToTimeout;
    }

    public Rover getRover() {
        return rover;
    }

    public MapModel getMap() {
        return map;
    }

    public List<String> getSymbols() {
        return symbols;
    }

    public Coordinate getSpaceShip() {
        return spaceShip;
    }

    public int getNumberOfSteps() {
        return numberOfSteps;
    }

    public void incrementNumberOfSteps() {
        this.numberOfSteps++;
    }
}
