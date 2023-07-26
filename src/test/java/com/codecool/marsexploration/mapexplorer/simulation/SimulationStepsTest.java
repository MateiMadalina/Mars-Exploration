package com.codecool.marsexploration.mapexplorer.simulation;

import com.codecool.marsexploration.mapexplorer.calculators.service.CoordinateCalculator;
import com.codecool.marsexploration.mapexplorer.calculators.service.CoordinateCalculatorImpl;
import com.codecool.marsexploration.mapexplorer.configuration.ExplorationSimulationConfiguration;
import com.codecool.marsexploration.mapexplorer.expeditionDeployer.MapExpeditionDeployer;
import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;
import com.codecool.marsexploration.mapexplorer.maploader.model.MapModel;
import com.codecool.marsexploration.mapexplorer.rovers.Rover;
import com.codecool.marsexploration.mapexplorer.rovers.placer.RoverDeployer;
import com.codecool.marsexploration.mapexplorer.spaceship.placer.SpaceshipDeployment;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SimulationStepsTest {
    private final List<String> symbols = List.of("#", "%", "*", "&");
    private final String[][] fakeRepresentation =
            {
                    {" ", "#", "#", "#", "*", " ", " ", " ", " "},
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
        MapModel mapModel = new MapModel(fakeRepresentation, true);
        Map<String, Set<Coordinate>> resourcesLocation = new HashMap<>();
        Coordinate roverCoordinate = new Coordinate(2,2);
        Coordinate spaceshipCoordinate = new Coordinate(1, 2);

        CoordinateCalculator calculator = new CoordinateCalculatorImpl(mapModel);
        Rover rover = new Rover(0, "rover-1", roverCoordinate, 2, resourcesLocation, calculator);

        RoverDeployer roverDeployer = new RoverDeployer(mapModel, calculator, rover);
        SpaceshipDeployment spaceshipDeployment = new SpaceshipDeployment(mapModel);
        ExplorationSimulationConfiguration explorationConfig = mock(ExplorationSimulationConfiguration.class);
        when(explorationConfig.landingSpot()).thenReturn(spaceshipCoordinate);

        MapExpeditionDeployer deployer = new MapExpeditionDeployer(mapModel, roverDeployer, spaceshipDeployment, explorationConfig);

        Simulation simulation = new Simulation(0, 1, rover, spaceshipCoordinate, mapModel, symbols, resourcesLocation);
        SimulationSteps simulationSteps = new SimulationSteps(calculator, simulation, deployer);

        simulationSteps.roverMovementRoutine();
    }
}