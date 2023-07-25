package com.codecool.marsexploration.mapexplorer.expeditionDeployer;

import com.codecool.marsexploration.mapexplorer.calculators.service.CoordinateCalculator;
import com.codecool.marsexploration.mapexplorer.calculators.service.CoordinateCalculatorImpl;
import com.codecool.marsexploration.mapexplorer.configuration.ConfigurationValidator;
import com.codecool.marsexploration.mapexplorer.configuration.ConfigurationValidatorImpl;
import com.codecool.marsexploration.mapexplorer.configuration.ExplorationSimulationConfiguration;
import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;
import com.codecool.marsexploration.mapexplorer.maploader.model.MapModel;
import com.codecool.marsexploration.mapexplorer.rovers.placer.RoverDeployer;
import com.codecool.marsexploration.mapexplorer.spaceship.placer.SpaceshipDeployment;
import org.junit.jupiter.api.Test;

import java.util.List;


class MapExpeditionDeployerTest {
    String[][] fakeMapRepresentation = {
            {" ", "#", "&"},
            {" ", "*", "%"},
            {"#", " ", "#"}
    };
    MapModel fakeMap = new MapModel(fakeMapRepresentation, true);
    CoordinateCalculator coordinateCalculator = new CoordinateCalculatorImpl(fakeMap);
    RoverDeployer roverDeployer = new RoverDeployer(fakeMap,coordinateCalculator);
    SpaceshipDeployment spaceshipDeployment = new SpaceshipDeployment(fakeMap);
    ExplorationSimulationConfiguration explorationSimulationConfiguration = new ExplorationSimulationConfiguration(fakeMap.toString(),new Coordinate(0,0), List.of("#","*","%"),5);
    ConfigurationValidator validator = new ConfigurationValidatorImpl(coordinateCalculator,explorationSimulationConfiguration);
    MapExpeditionDeployer mapExpeditionDeployer = new MapExpeditionDeployer(fakeMap,roverDeployer,spaceshipDeployment,coordinateCalculator,validator);

    @Test
    void sendExpedition() {
        mapExpeditionDeployer.sendExpedition();
        System.out.println(fakeMap.toString());
    }
}