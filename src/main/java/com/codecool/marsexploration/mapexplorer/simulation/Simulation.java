package com.codecool.marsexploration.mapexplorer.simulation;

import com.codecool.marsexploration.mapexplorer.map.Coordinate;
import com.codecool.marsexploration.mapexplorer.map.MapModel;
import com.codecool.marsexploration.mapexplorer.rovers.Rover;

import java.util.List;

public class Simulation {
    private int numberOfSteps;
    private final int stepsToTimeout;
    private final List<Rover> rovers;
    private final Coordinate spaceShip;
    private final MapModel map;
    private final List<String> symbols;

    public Simulation(int stepsToTimeout, List<Rover> rovers, Coordinate spaceShip, MapModel map, List<String> symbols) {
        this.numberOfSteps = 0;
        this.stepsToTimeout = stepsToTimeout;
        this.rovers = rovers;
        this.spaceShip = spaceShip;
        this.map = map;
        this.symbols = symbols;
    }

    public int getStepsToTimeout() {
        return stepsToTimeout;
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
    public List<Rover> getRovers() {
        return rovers;
    }
    public void addToRovers(Rover rover) {
        rovers.add(rover);
    }
}
