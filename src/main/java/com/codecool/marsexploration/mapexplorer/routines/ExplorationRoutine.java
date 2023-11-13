package com.codecool.marsexploration.mapexplorer.routines;

import com.codecool.marsexploration.mapexplorer.expeditionDeployer.MapExpeditionDeployer;
import com.codecool.marsexploration.mapexplorer.logger.Logger;
import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;
import com.codecool.marsexploration.mapexplorer.service.outcome.Analyzer;
import com.codecool.marsexploration.mapexplorer.simulation.Simulation;

import java.util.List;

public class ExplorationRoutine implements Routine{
    private final Simulation simulation;
    private final MapExpeditionDeployer deployer;
    private final Logger consoleLogger;
    private final Logger fileLogger;
    private final List<Analyzer> analyzers;
    private boolean isColonizable = false;

    public ExplorationRoutine(Simulation simulation, MapExpeditionDeployer deployer, Logger consoleLogger, Logger fileLogger, List<Analyzer> analyzers) {
        this.simulation = simulation;
        this.deployer = deployer;
        this.consoleLogger = consoleLogger;
        this.fileLogger = fileLogger;
        this.analyzers = analyzers;
    }
    @Override
    public void run() {
        sendExpeditionDelegation();
        registerRoverStep();
        scanForResourcesAndAddToMap();
        explorationProtocol();
    }
    private void sendExpeditionDelegation() {
        deployer.sendExpedition();
        consoleLogger.log("Rover deployed at coordinate: " + simulation.getRovers().get(0).getCurrentPosition());
        fileLogger.log("Rover deployed at coordinate: " + simulation.getRovers().get(0).getCurrentPosition());
        System.out.println("\n");
    }

    private void explorationProtocol() {
        for (int i = 0; i < simulation.getStepsToTimeout(); i++) {
            Coordinate pickedCoordinate = simulation.getRovers().get(0).pickStep();
            while (simulation.getRovers().get(0).getPickedSteps().contains(pickedCoordinate)) {
                simulation.incrementNumberOfSteps();
                pickedCoordinate = simulation.getRovers().get(0).pickStep();
                isColonizable = analyzers.get(0).analyze();
                if (isColonizable || simulation.getNumberOfSteps() > simulation.getStepsToTimeout()) {
                    i = simulation.getStepsToTimeout();
                    break;
                }
            }

            roverReturnToSpaceship();
            simulation.getRovers().get(0).addToPickedSteps(pickedCoordinate);
            simulation.getRovers().get(0).setCurrentPosition(pickedCoordinate);
            consoleLogger.log("Rover moves to coordinate: " + simulation.getRovers().get(0).getCurrentPosition());
            fileLogger.log("Rover moves to coordinate: " + simulation.getRovers().get(0).getCurrentPosition());
            scanForResourcesAndAddToMap();
        }

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

    private void registerRoverStep() {
        simulation.getRovers().get(0).addToPickedSteps(simulation.getRovers().get(0).getCurrentPosition());
    }

    private void scanForResourcesAndAddToMap() {
        simulation.getRovers().get(0).addToResourceMap(simulation.getMap(), simulation.getSymbols());
    }

    private void roverReturnToSpaceship() {
        simulation.getRovers().get(0).moveToDestination(simulation.getSpaceShip());
    }

    private void logResources(String symbol, String resource) {
        if(simulation.getRovers().get(0).getResourceLocations().get(symbol) != null) {
            consoleLogger.log(resource + simulation.getRovers().get(0).getResourceLocations().get(symbol).size());
            fileLogger.log(resource + simulation.getRovers().get(0).getResourceLocations().get(symbol).size());
        }
    }

    public boolean isColonizable() {
        return isColonizable;
    }

}
