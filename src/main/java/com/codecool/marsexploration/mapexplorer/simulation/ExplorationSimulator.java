package com.codecool.marsexploration.mapexplorer.simulation;

import com.codecool.marsexploration.mapexplorer.exploration.ExplorationOutcome;
import com.codecool.marsexploration.mapexplorer.logger.Logger;
import com.codecool.marsexploration.mapexplorer.outcome.service.Analyzer;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ExplorationSimulator {
    private final Simulation simulation;
    private final SimulationSteps simulationSteps;
    private final Logger consoleLogger;
    private final Logger fileLogger;
    private final List<Analyzer> analyzers;

    public ExplorationSimulator(Simulation simulation, SimulationSteps simulationSteps, Logger consoleLogger,
                                Logger fileLogger, List<Analyzer> analyzers) {
        this.simulation = simulation;
        this.simulationSteps = simulationSteps;
        this.consoleLogger = consoleLogger;
        this.fileLogger = fileLogger;
        this.analyzers = analyzers;
    }

    public void run() {
        clearFile();
        consoleLogger.log("Map to explore:");
        fileLogger.log("Map to explore:");
        consoleLogger.log(simulation.getMap().toString());
        fileLogger.log(simulation.getMap().toString());
        consoleLogger.log("Spaceship lands at coordinate: " + simulation.getSpaceShip());
        fileLogger.log("Spaceship lands at coordinate: " + simulation.getSpaceShip());
        simulationSteps.roverMovementRoutine();

        String analysisResult = String.valueOf(analyzers.get(0).analyze() ? ExplorationOutcome.COLONIZABLE : ExplorationOutcome.NOT_COLONIZABLE);

        consoleLogger.log("Planet colonizable: " + analysisResult);
        fileLogger.log("Planet colonizable: " + analysisResult);
    }

    private void clearFile() {
        String filePath = "src/main/resources/exploration-outcome.txt";

        try {
            FileWriter fileWriter = new FileWriter(filePath);
            fileWriter.write("");
            fileWriter.close();
            System.out.println("Contents of the file have been cleared successfully.");
        } catch (IOException e) {
            System.err.println("An error occurred while clearing the file: " + e.getMessage());
        }
    }
}
