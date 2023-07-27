package com.codecool.marsexploration.mapexplorer;

import com.codecool.marsexploration.mapexplorer.calculators.service.CoordinateCalculator;
import com.codecool.marsexploration.mapexplorer.calculators.service.CoordinateCalculatorImpl;
import com.codecool.marsexploration.mapexplorer.configuration.ExplorationSimulationConfiguration;
import com.codecool.marsexploration.mapexplorer.expeditionDeployer.MapExpeditionDeployer;
import com.codecool.marsexploration.mapexplorer.input.service.MapFileReader;
import com.codecool.marsexploration.mapexplorer.input.service.MapFileReaderImpl;
import com.codecool.marsexploration.mapexplorer.logger.ConsoleLogger;
import com.codecool.marsexploration.mapexplorer.logger.Logger;
import com.codecool.marsexploration.mapexplorer.maploader.MapLoader;
import com.codecool.marsexploration.mapexplorer.maploader.MapLoaderImpl;
import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;
import com.codecool.marsexploration.mapexplorer.maploader.model.MapModel;
import com.codecool.marsexploration.mapexplorer.outcome.service.Analyzer;
import com.codecool.marsexploration.mapexplorer.outcome.service.LackOfResourcesAnalyzer;
import com.codecool.marsexploration.mapexplorer.rovers.Rover;
import com.codecool.marsexploration.mapexplorer.rovers.placer.RoverDeployer;
import com.codecool.marsexploration.mapexplorer.simulation.ExplorationSimulator;
import com.codecool.marsexploration.mapexplorer.simulation.Simulation;
import com.codecool.marsexploration.mapexplorer.simulation.SimulationSteps;
import com.codecool.marsexploration.mapexplorer.spaceship.placer.SpaceshipDeployment;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Application {
    private static final String workDir = "src/main";

    public static void main(String[] args) throws FileNotFoundException {
        String mapFile = workDir + "/resources/exploration-0.map";

        Map<String, Set<Coordinate>> resourceLocations = new HashMap<>();
        int stepsToTimeout = 300;
        List<String> symbols = List.of("#", "&", "%", "*");
        Logger consoleLogger = new ConsoleLogger();
        MapFileReader mapFileReader = new MapFileReaderImpl();
        MapLoader mapLoader = new MapLoaderImpl(mapFileReader);
        MapModel currentMap = mapLoader.load(mapFile);
        CoordinateCalculator coordinateCalculator = new CoordinateCalculatorImpl(currentMap);
        Coordinate spaceshipCoordinate = coordinateCalculator.getRandomLandingCoordinate(currentMap);
        Rover rover = new Rover(1, "rover-1", null, 2, resourceLocations, coordinateCalculator);
        Simulation simulation = new Simulation(stepsToTimeout, rover, spaceshipCoordinate, currentMap, symbols);
        RoverDeployer roverDeployer = new RoverDeployer(currentMap, coordinateCalculator, rover);
        SpaceshipDeployment spaceshipDeployment = new SpaceshipDeployment(currentMap);
        ExplorationSimulationConfiguration explorationConfig = new ExplorationSimulationConfiguration(
                mapFile, spaceshipCoordinate, symbols, stepsToTimeout);
        MapExpeditionDeployer mapExpeditionDeployer = new MapExpeditionDeployer(currentMap, roverDeployer,
                spaceshipDeployment, explorationConfig);
        SimulationSteps simulationSteps = new SimulationSteps(coordinateCalculator, simulation, mapExpeditionDeployer, consoleLogger);
        Analyzer lackOfResourcesAnalyzer = new LackOfResourcesAnalyzer(simulation, coordinateCalculator);
        List<Analyzer> analyzers = List.of(lackOfResourcesAnalyzer);
        ExplorationSimulator explorationSimulator = new ExplorationSimulator(simulation, explorationConfig, simulationSteps, consoleLogger, analyzers);

        explorationSimulator.run();
    }
}

