package com.codecool.marsexploration.mapexplorer.routines;

import com.codecool.marsexploration.mapexplorer.commandCenter.CommandCenter;
import com.codecool.marsexploration.mapexplorer.commandCenter.Status;
import com.codecool.marsexploration.mapexplorer.logger.Logger;
import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;
import com.codecool.marsexploration.mapexplorer.rovers.Rover;
import com.codecool.marsexploration.mapexplorer.calculators.CoordinateCalculator;

import java.util.*;

public class BuildingRoutine implements Routine {
    private final CoordinateCalculator calculator;
    private final Rover rover;
    private CommandCenter commandCenter;
    private final Logger consoleLogger;
    private final Logger fileLogger;

    public BuildingRoutine(CoordinateCalculator calculator, Rover rover , CommandCenter commandCenter, Logger consoleLogger,Logger fileLogger) {
        this.calculator = calculator;
        this.rover = rover;
        this.commandCenter = commandCenter;
        this.consoleLogger = consoleLogger;
        this.fileLogger = fileLogger;
    }

    @Override
    public void run() {
        sendFirstRoverToResource();
        placeCommandCenter();
        startExtractionForFirstMineral();
        builtCommandCenter();
    }

    public void placeCommandCenter(){
        List<Coordinate> allCoordinateNearResources = (List<Coordinate>) calculator.getAdjacentCoordinates(rover.getCurrentPosition(),1);
        List<Coordinate> emptyCoordinateNearResources = calculator.gatAllPossiblePlacementsForRoverWithEmptySpaceAdjacent(allCoordinateNearResources);
        Random random = new Random();
        Coordinate centerPlacement = emptyCoordinateNearResources.get(random.nextInt(emptyCoordinateNearResources.size()));
        System.out.println("\n");
        consoleLogger.log("Selection of command center location: " + centerPlacement);
        fileLogger.log("Selection of command center location: " + centerPlacement);
        consoleLogger.log("Command center created with rover resources");
        fileLogger.log("Command center created with rover resources");
        System.out.println("\n");
        Map<String,List<Coordinate>> resourceOnStock = new HashMap<>();
        commandCenter = new CommandCenter(centerPlacement, Status.PENDING, resourceOnStock);
    }

    public Coordinate getRandomResources(){
        Random random = new Random();
        Set<Coordinate> minerals = null;
        for(String key : rover.getResourceLocations().keySet()){
            if(key.equals("%")){
                minerals = rover.getResourceLocations().get(key);
            }
        }
        List<Coordinate> coordinates = new ArrayList<>(minerals);
        return coordinates.get(random.nextInt(minerals.size()));
    }

    private void builtCommandCenter() {
        commandCenter.setStatus(Status.BUILT);
    }

    private void sendFirstRoverToResource(){
        Coordinate choosenResourceCoordinate = getRandomResources();
        consoleLogger.log("Rover select the mineral located at: " + choosenResourceCoordinate);
        fileLogger.log("Rover select the mineral located at: " + choosenResourceCoordinate);
        consoleLogger.log("Rover starts extraction");
        fileLogger.log("Rover starts extraction");
        consoleLogger.log("Rover has collected enough materials");
        fileLogger.log("Rover has collected enough materials");
        rover.setCurrentPosition(choosenResourceCoordinate);
    }
    private void startExtractionForFirstMineral(){
        commandCenter.addResourcesToCommandCenter("%",rover.getCurrentPosition());
    }

    public CommandCenter getCommandCenter() {
        return commandCenter;
    }
}
