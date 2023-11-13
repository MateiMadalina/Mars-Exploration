package com.codecool.marsexploration.mapexplorer.expeditionDeployer;

import com.codecool.marsexploration.mapexplorer.calculators.CoordinateCalculator;
import com.codecool.marsexploration.mapexplorer.calculators.CoordinateCalculatorImpl;
import com.codecool.marsexploration.mapexplorer.configuration.ExplorationSimulationConfiguration;
import com.codecool.marsexploration.mapexplorer.map.Coordinate;
import com.codecool.marsexploration.mapexplorer.map.MapModel;
import com.codecool.marsexploration.mapexplorer.rovers.Rover;
import com.codecool.marsexploration.mapexplorer.rovers.placer.RoverDeployer;
import com.codecool.marsexploration.mapexplorer.spaceship.SpaceshipDeployer;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.mock;

class MapExpeditionDeployerTest {
    String[][] fakeMapRepresentation = {
            {" ", "#", "&"},
            {" ", "*", "%"},
            {"#", " ", "#"}
    };
    MapModel fakeMap = new MapModel(fakeMapRepresentation);
    Rover rover = mock(Rover.class);
    CoordinateCalculator coordinateCalculator = new CoordinateCalculatorImpl(fakeMap);
    RoverDeployer roverDeployer = new RoverDeployer(fakeMap,coordinateCalculator, rover);
    SpaceshipDeployer spaceshipDeployer = new SpaceshipDeployer(fakeMap);
    Coordinate spaceshipCoordinate = coordinateCalculator.getRandomLandingCoordinate(fakeMap);
    ExplorationSimulationConfiguration explorationSimulationConfig = new ExplorationSimulationConfiguration(" ",spaceshipCoordinate,List.of("#","*"),5);
    MapExpeditionDeployer mapExpeditionDeployer = new MapExpeditionDeployer(fakeMap,roverDeployer, spaceshipDeployer,explorationSimulationConfig);

    @Test
    void sendExpedition() {
        mapExpeditionDeployer.sendExpedition();
        System.out.println(fakeMap.toString());
    }
}