package com.codecool.marsexploration.mapexplorer.expeditionDeployer;

import com.codecool.marsexploration.mapexplorer.calculators.service.CoordinateCalculator;
import com.codecool.marsexploration.mapexplorer.configuration.ConfigurationValidator;
import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;
import com.codecool.marsexploration.mapexplorer.maploader.model.Map;
import com.codecool.marsexploration.mapexplorer.rovers.placer.RoverDeployer;
import com.codecool.marsexploration.mapexplorer.spaceship.placer.SpaceshipDeployment;

public class MapExpeditionDeployer {
    private final Map map;
    private final RoverDeployer roverDeployer;
    private final SpaceshipDeployment spaceshipDeployment;
    private final CoordinateCalculator calculator;
    private final ConfigurationValidator validator;


    public MapExpeditionDeployer(Map map, RoverDeployer roverDeployer, SpaceshipDeployment spaceshipDeployment,
                                 CoordinateCalculator calculator, ConfigurationValidator validator) {
        this.map = map;
        this.roverDeployer = roverDeployer;
        this.spaceshipDeployment = spaceshipDeployment;
        this.calculator = calculator;
        this.validator = validator;
    }

    public void sendExpedition(){
        Coordinate spaceshipCoordinate = calculator.getRandomLandingCoordinate(map);
        while(!validator.validate(map,spaceshipCoordinate)){
            spaceshipCoordinate = calculator.getRandomLandingCoordinate(map);
        }
        Coordinate roverCoordinate = roverDeployer.getPlacement(spaceshipCoordinate);
        roverCoordinate = validateRoverCoordinate(roverCoordinate,spaceshipCoordinate);
        spaceshipDeployment.place(spaceshipCoordinate);
        roverDeployer.place(roverCoordinate);
    }

    private Coordinate validateRoverCoordinate(Coordinate roverCoordinate , Coordinate spaceshipCoordinate){
        while(!map.getRepresentation()[roverCoordinate.x()][roverCoordinate.y()].equals(" ")){
            roverCoordinate = roverDeployer.getPlacement(spaceshipCoordinate);
        }
        return roverCoordinate;
    }
}
