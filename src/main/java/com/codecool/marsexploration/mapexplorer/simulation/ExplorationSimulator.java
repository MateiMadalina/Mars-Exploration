package com.codecool.marsexploration.mapexplorer.simulation;

import com.codecool.marsexploration.mapexplorer.configuration.ConfigurationValidator;
import com.codecool.marsexploration.mapexplorer.configuration.ConfigurationValidatorImpl;
import com.codecool.marsexploration.mapexplorer.configuration.ExplorationSimulationConfiguration;
import com.codecool.marsexploration.mapexplorer.expeditionDeployer.MapExpeditionDeployer;
import com.codecool.marsexploration.mapexplorer.logger.ConsoleLogger;
import com.codecool.marsexploration.mapexplorer.logger.FileLogger;
import com.codecool.marsexploration.mapexplorer.logger.Logger;
import com.codecool.marsexploration.mapexplorer.map.*;
import com.codecool.marsexploration.mapexplorer.map.Coordinate;
import com.codecool.marsexploration.mapexplorer.map.MapModel;
import com.codecool.marsexploration.mapexplorer.routines.BuildingRoutine;
import com.codecool.marsexploration.mapexplorer.routines.ExplorationRoutine;
import com.codecool.marsexploration.mapexplorer.routines.ExtractionRoutine;
import com.codecool.marsexploration.mapexplorer.routines.Routine;
import com.codecool.marsexploration.mapexplorer.models.rovers.Rover;
import com.codecool.marsexploration.mapexplorer.models.rovers.RoverDeployer;
import com.codecool.marsexploration.mapexplorer.calculators.CoordinateCalculator;
import com.codecool.marsexploration.mapexplorer.calculators.CoordinateCalculatorImpl;
import com.codecool.marsexploration.mapexplorer.outcome.Analyzer;
import com.codecool.marsexploration.mapexplorer.outcome.LackOfResourcesAnalyzer;
import com.codecool.marsexploration.mapexplorer.models.spaceship.SpaceshipDeployer;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class ExplorationSimulator {
    String mapFile = "src/main/resources/exploration-0.map";
    Map<String, Set<Coordinate>> resourceLocations = new HashMap<>();
    int stepsToTimeout = 300;
    List<String> symbols = List.of("#", "&", "%", "*");
    Logger consoleLogger = new ConsoleLogger();
    Logger fileLogger = new FileLogger("src/main/resources/exploration-outcome.txt");

    MapModel currentMap;

    {
        try {
            currentMap = MapLoader.load(mapFile,32,32);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    CoordinateCalculator coordinateCalculator = new CoordinateCalculatorImpl(currentMap);
    Coordinate spaceshipCoordinate = coordinateCalculator.getRandomLandingCoordinate(currentMap);
    Rover rover = new Rover( null, 2, resourceLocations, coordinateCalculator);
    List<Rover> rovers = new ArrayList<>(List.of(rover));
    Simulation simulation = new Simulation(stepsToTimeout, rovers, spaceshipCoordinate, currentMap, symbols);
    SpaceshipDeployer spaceshipDeployer = new SpaceshipDeployer(currentMap);
    RoverDeployer roverDeployer = new RoverDeployer(currentMap, coordinateCalculator, rover);
    ExplorationSimulationConfiguration explorationConfig = new ExplorationSimulationConfiguration(mapFile, spaceshipCoordinate, symbols, stepsToTimeout);
    ConfigurationValidator configurationValidator = new ConfigurationValidatorImpl(coordinateCalculator,explorationConfig);

    MapExpeditionDeployer mapExpeditionDeployer = new MapExpeditionDeployer(currentMap, roverDeployer, spaceshipDeployer, explorationConfig);
    Analyzer lackOfResourcesAnalyzer = new LackOfResourcesAnalyzer(simulation, coordinateCalculator, fileLogger);
    List<Analyzer> analyzers = List.of(lackOfResourcesAnalyzer);

    BuildingRoutine buildingRoutine = new BuildingRoutine(coordinateCalculator,rover,null,consoleLogger,fileLogger);

    List<Routine> routines = List.of(
            new ExplorationRoutine(simulation, mapExpeditionDeployer, consoleLogger, fileLogger, analyzers),
            buildingRoutine,
            new ExtractionRoutine(simulation,coordinateCalculator,consoleLogger,fileLogger,buildingRoutine.getCommandCenter())
    );
    SimulationSteps simulationSteps = new SimulationSteps(routines);

    public ExplorationSimulator() {
        try{
            currentMap = MapLoader.load(mapFile,32,32);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void run() {
        if(configurationValidator.validate(currentMap)) {
            clearFile();
            consoleLogger.log("Legend:\n # - Mountains  \n & - Pits \n % - Minerals \n * - Pockets Of Water \n");
            consoleLogger.log("Map to explore:");
            fileLogger.log("Map to explore:");
            consoleLogger.log(simulation.getMap().toString());
            fileLogger.log(simulation.getMap().toString());
            consoleLogger.log("Spaceship lands at coordinate: " + simulation.getSpaceShip());
            fileLogger.log("Spaceship lands at coordinate: " + simulation.getSpaceShip());
            simulationSteps.runRoutines();

            String analysisResult = analyzers.get(0).analyze() ? "COLONIZABLE" : "NOT COLONIZABLE";

            consoleLogger.log("Planet colonizable: " + analysisResult);
            fileLogger.log("Planet colonizable: " + analysisResult);
        }else {
            consoleLogger.logError("Invalid Map!!!");
            fileLogger.logError("Ivalid Map!!!");
        }
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
