package com.codecool.marsexploration.mapexplorer.simulation;

import com.codecool.marsexploration.mapexplorer.calculators.CoordinateCalculator;
import com.codecool.marsexploration.mapexplorer.calculators.CoordinateCalculatorImpl;
import com.codecool.marsexploration.mapexplorer.configuration.ExplorationSimulationConfiguration;
import com.codecool.marsexploration.mapexplorer.expeditionDeployer.MapExpeditionDeployer;
import com.codecool.marsexploration.mapexplorer.logger.ConsoleLogger;
import com.codecool.marsexploration.mapexplorer.logger.FileLogger;
import com.codecool.marsexploration.mapexplorer.logger.Logger;
import com.codecool.marsexploration.mapexplorer.map.Coordinate;
import com.codecool.marsexploration.mapexplorer.map.MapModel;
import com.codecool.marsexploration.mapexplorer.outcome.Analyzer;
import com.codecool.marsexploration.mapexplorer.outcome.LackOfResourcesAnalyzer;
import com.codecool.marsexploration.mapexplorer.routines.ExplorationRoutine;
import com.codecool.marsexploration.mapexplorer.routines.Routine;
import com.codecool.marsexploration.mapexplorer.rovers.Rover;
import com.codecool.marsexploration.mapexplorer.rovers.placer.RoverDeployer;
import com.codecool.marsexploration.mapexplorer.spaceship.SpaceshipDeployer;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SimulationStepsTest {
    private final List<String> symbols = List.of("#", "%", "*", "&");
    private final Logger consoleLogger = new ConsoleLogger();
    private final Logger fileLogger = new FileLogger("src/main/resources/exploration-outcome.txt");
    private final String[][] fakeRepresentation =
            {
                    {" ", "#", "#", "#", " ", " ", " ", " ", " "},
                    {"#", "*", "$", " ", "%", " ", " ", " ", " "},
                    {" ", "#", " ", "*", "%", " ", " ", " ", " "},
                    {"*", "#", "#", " ", " ", " ", " ", " ", " "},
                    {" ", "#", "&", "&", " ", " ", " ", " ", " "},
                    {" ", "#", "&", "&", " ", " ", " ", " ", " "},
                    {" ", "#", "&", "&", " ", " ", " ", " ", " "},
                    {" ", "#", "&", "&", " ", " ", " ", " ", " "},
                    {" ", "#", "&", "&", " ", " ", " ", " ", " "}
            };

    @Test
    void roverMovementRoutine() {
        MapModel mapModel = new MapModel(fakeRepresentation);
        Map<String, Set<Coordinate>> resourcesLocation = new HashMap<>();
        Coordinate spaceshipCoordinate = new Coordinate(1, 2);

        CoordinateCalculator calculator = new CoordinateCalculatorImpl(mapModel);
        Rover rover = new Rover( null, 2, resourcesLocation, calculator);

        RoverDeployer roverDeployer = new RoverDeployer(mapModel, calculator, rover);
        SpaceshipDeployer spaceshipDeployment = new SpaceshipDeployer(mapModel);
        ExplorationSimulationConfiguration explorationConfig = mock(ExplorationSimulationConfiguration.class);
        when(explorationConfig.landingSpot()).thenReturn(spaceshipCoordinate);

        MapExpeditionDeployer deployer = new MapExpeditionDeployer(mapModel, roverDeployer, spaceshipDeployment, explorationConfig);
        List<Rover> rovers = new ArrayList<>(List.of(rover));

        Simulation simulation = new Simulation(100, rovers, spaceshipCoordinate, mapModel, symbols);
        Analyzer lackOfResourcesAnalyzer = new LackOfResourcesAnalyzer(simulation, calculator, fileLogger);
        List<Analyzer> analyzers = List.of(lackOfResourcesAnalyzer);
        List<Routine> routines = List.of(new ExplorationRoutine(simulation, deployer, consoleLogger, fileLogger, analyzers));
        SimulationSteps simulationSteps = new SimulationSteps(routines);

        simulationSteps.runRoutines();
    }
}