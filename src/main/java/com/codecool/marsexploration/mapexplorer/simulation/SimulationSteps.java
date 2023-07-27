package com.codecool.marsexploration.mapexplorer.simulation;

import com.codecool.marsexploration.mapexplorer.expeditionDeployer.MapExpeditionDeployer;
import com.codecool.marsexploration.mapexplorer.logger.Logger;
import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;

public class SimulationSteps {
    private final Simulation simulation;
    private final MapExpeditionDeployer deployer;
    private final Logger consoleLogger;
    private final Logger fileLogger;

    public SimulationSteps(Simulation simulation, MapExpeditionDeployer deployer, Logger consoleLogger, Logger fileLogger) {
        this.simulation = simulation;
        this.deployer = deployer;
        this.consoleLogger = consoleLogger;
        this.fileLogger = fileLogger;
    }

    public void roverMovementRoutine(){
        deployer.sendExpedition();
        consoleLogger.log("Rover deployed at coordinate: " + simulation.getRover().getCurrentPosition());
        fileLogger.log("Rover deployed at coordinate: " + simulation.getRover().getCurrentPosition());
        System.out.println("\n");

        simulation.getRover().addToPickedSteps(simulation.getRover().getCurrentPosition());
        simulation.getRover().addToResourceMap(simulation.getMap(), simulation.getSymbols());

        for (int i = 0; i < simulation.getStepsToTimeout(); i++) {
            Coordinate pickedCoordinate = simulation.getRover().pickStep();
            while (simulation.getRover().getPickedSteps().contains(pickedCoordinate)) {
                simulation.incrementNumberOfSteps();
                pickedCoordinate = simulation.getRover().pickStep();
                if (simulation.getNumberOfSteps() > simulation.getStepsToTimeout()) {
                    i = simulation.getStepsToTimeout();
                    break;
                }
            }
            simulation.getRover().addToPickedSteps(pickedCoordinate);
            simulation.getRover().setCurrentPosition(pickedCoordinate);
            consoleLogger.log("Rover moves to coordinate: " + simulation.getRover().getCurrentPosition());
            fileLogger.log("Rover moves to coordinate: " + simulation.getRover().getCurrentPosition());
            simulation.getRover().addToResourceMap(simulation.getMap(), simulation.getSymbols());
        }

        simulation.getRover().returnToSpaceship(simulation.getSpaceShip());
        System.out.println("\n");
        fileLogger.log("\n");
        consoleLogger.log("Resources found by Rover:");
        fileLogger.log("Resources found by Rover:");
        logResources("#", "Mountains: ");
        logResources("&", "Pits: ");
        logResources("%", "Minerals: ");
        logResources("*", "Pockets of water: ");
        System.out.println("\n");
    }

    private void logResources(String symbol, String resource) {
        if(simulation.getRover().getResourceLocations().get(symbol) != null) {
            consoleLogger.log(resource + simulation.getRover().getResourceLocations().get(symbol).size());
            fileLogger.log(resource + simulation.getRover().getResourceLocations().get(symbol).size());
        }
    }
}
