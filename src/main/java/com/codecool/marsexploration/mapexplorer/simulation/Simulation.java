package com.codecool.marsexploration.mapexplorer.simulation;

import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;
import com.codecool.marsexploration.mapexplorer.maploader.model.MapModel;
import com.codecool.marsexploration.mapexplorer.rovers.Rover;

import java.util.List;
import java.util.Map;

public class Simulation {
    private final int numberOfSteps;
    private final int stepsToTimeout;
    private final Rover rover;
    private final Coordinate spaceShip;
    private final MapModel map;
    private final List<String> symbols;
    private final Map<String,List<Coordinate>> explorationOutcome;

    public Simulation(int numberOfSteps, int stepsToTimeout, Rover rover, Coordinate spaceShip, MapModel map, List<String> symbols, Map<String, List<Coordinate>> explorationOutcome) {
        this.numberOfSteps = numberOfSteps;
        this.stepsToTimeout = stepsToTimeout;
        this.rover = rover;
        this.spaceShip = spaceShip;
        this.map = map;
        this.symbols = symbols;

        this.explorationOutcome = explorationOutcome;
    }
}
