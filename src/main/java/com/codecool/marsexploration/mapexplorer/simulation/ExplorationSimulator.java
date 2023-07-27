package com.codecool.marsexploration.mapexplorer.simulation;

import com.codecool.marsexploration.mapexplorer.configuration.ExplorationSimulationConfiguration;
import com.codecool.marsexploration.mapexplorer.logger.Logger;
import com.codecool.marsexploration.mapexplorer.outcome.service.Analyzer;

import java.util.List;

public class ExplorationSimulator {
    private final Simulation simulation;
    private final ExplorationSimulationConfiguration explorationSimulationConfiguration;
    private final SimulationSteps simulationSteps;
    private final Logger consoleLogger;

    private final List<Analyzer> analyzers;

    public ExplorationSimulator(Simulation simulation,
                                ExplorationSimulationConfiguration explorationSimulationConfiguration,
                                SimulationSteps simulationSteps,
                                Logger consoleLogger, List<Analyzer> analyzers) {
        this.simulation = simulation;
        this.explorationSimulationConfiguration = explorationSimulationConfiguration;
        this.simulationSteps = simulationSteps;
        this.consoleLogger = consoleLogger;
        this.analyzers = analyzers;
    }

    public void run() {
        consoleLogger.log("Map to explore:");
        consoleLogger.log(simulation.getMap().toString());
        consoleLogger.log("Spaceship lands at coordinate: " + simulation.getSpaceShip());
        simulationSteps.roverMovementRoutine();

        consoleLogger.log("Analysis of enough resources result: " + analyzers.get(0).analyze());
    }
}
