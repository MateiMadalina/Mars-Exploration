package com.codecool.marsexploration.mapexplorer.expeditionDeployer;

import com.codecool.marsexploration.mapexplorer.configuration.ExplorationSimulationConfiguration;
import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;
import com.codecool.marsexploration.mapexplorer.maploader.model.MapModel;
import com.codecool.marsexploration.mapexplorer.rovers.placer.RoverDeployer;
import com.codecool.marsexploration.mapexplorer.spaceship.SpaceshipDeployer;

public class MapExpeditionDeployer {
    private final MapModel map;
    private final RoverDeployer roverDeployer;
    private final SpaceshipDeployer spaceshipDeployer;
    private final ExplorationSimulationConfiguration explorationSimulationConfig;

    public MapExpeditionDeployer(MapModel map, RoverDeployer roverDeployer, SpaceshipDeployer spaceshipDeployer, ExplorationSimulationConfiguration explorationSimulationConfig) {
        this.map = map;
        this.roverDeployer = roverDeployer;
        this.spaceshipDeployer = spaceshipDeployer;
        this.explorationSimulationConfig = explorationSimulationConfig;
    }

    public void sendExpedition(){
        Coordinate spaceshipCoordinate = explorationSimulationConfig.landingSpot();

        Coordinate roverCoordinate = validateRoverCoordinate(spaceshipCoordinate);
        spaceshipDeployer.place(spaceshipCoordinate);
        roverDeployer.place(roverCoordinate);
    }

    private Coordinate validateRoverCoordinate(Coordinate spaceshipCoordinate){
        Coordinate roverCoordinate = roverDeployer.getPlacement(spaceshipCoordinate);
        while(!map.representation()[roverCoordinate.x()][roverCoordinate.y()].equals(" ")){
            roverCoordinate = roverDeployer.getPlacement(spaceshipCoordinate);
        }
        return roverCoordinate;
    }
}
