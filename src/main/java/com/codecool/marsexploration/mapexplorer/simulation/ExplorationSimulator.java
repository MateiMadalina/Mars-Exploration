package com.codecool.marsexploration.mapexplorer.simulation;

import com.codecool.marsexploration.mapexplorer.configuration.ExplorationSimulationConfiguration;

public class ExplorationSimulator {
    private final Simulation simulationContext;
    private final ExplorationSimulationConfiguration explorationSimulationConfiguration;

    public ExplorationSimulator(Simulation simulationContext, ExplorationSimulationConfiguration explorationSimulationConfiguration) {
        this.simulationContext = simulationContext;
        this.explorationSimulationConfiguration = explorationSimulationConfiguration;
    }
}
