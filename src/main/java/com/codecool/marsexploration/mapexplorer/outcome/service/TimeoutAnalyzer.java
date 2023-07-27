package com.codecool.marsexploration.mapexplorer.outcome.service;

import com.codecool.marsexploration.mapexplorer.simulation.Simulation;

public class TimeoutAnalyzer implements Analyzer{
    private final Simulation simulation;

    public TimeoutAnalyzer(Simulation simulation) {
        this.simulation = simulation;
    }

    @Override
    public boolean analyze() {
        int numberOfActualSteps = simulation.getNumberOfSteps();
        int stepsToTimeout = simulation.getStepsToTimeout();

        return numberOfActualSteps < stepsToTimeout;
    }
}
