package com.codecool.marsexploration.mapexplorer.routines;

import com.codecool.marsexploration.mapexplorer.commandCenter.CommandCenter;
import com.codecool.marsexploration.mapexplorer.logger.Logger;
import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;
import com.codecool.marsexploration.mapexplorer.rovers.Rover;
import com.codecool.marsexploration.mapexplorer.service.calculators.CoordinateCalculator;
import com.codecool.marsexploration.mapexplorer.simulation.Simulation;

import java.util.*;

public class ExtractionRoutine implements Routine{

    private final Simulation simulation;
    private final CoordinateCalculator calculator;
    private final Logger consoleLogger;
    private final Logger fileLogger;
    private CommandCenter commandCenter;

    public ExtractionRoutine(Simulation simulation, CoordinateCalculator calculator, Logger consoleLogger,
                             Logger fileLogger, CommandCenter commandCenter) {

        this.simulation = simulation;
        this.calculator = calculator;
        this.consoleLogger = consoleLogger;
        this.fileLogger = fileLogger;
        this.commandCenter = commandCenter;
    }

    @Override
    public void run() {
        consoleLogger.log("Extration protocol initiated...");
        fileLogger.log("Extration protocol initiated...");
        consoleLogger.log("All available resource sites: " + getResourcesLocatedNearCommandCenter());
        fileLogger.log("All available resource sites: " + getResourcesLocatedNearCommandCenter());
        System.out.println("\n");
        consoleLogger.log("First rover extracted enough minerals to build other rovers");
        fileLogger.log("First rover extracted enough minerals to build other rovers");
        System.out.println("\n");
        placeAllRovers();
    }

    public List<Coordinate> getResourcesLocatedNearCommandCenter() {
        List<Coordinate> adjacentCoordinates = (List<Coordinate>) calculator.getAdjacentCoordinates(commandCenter.getLocation(), 10);
        String[][] mapRepresentation = simulation.getMap().getRepresentation();
        List<Coordinate> availableResources = new ArrayList<>(adjacentCoordinates.stream()
                .filter(coordinate -> mapRepresentation[coordinate.x()][coordinate.y()].equals("%")
                        || mapRepresentation[coordinate.x()][coordinate.y()].equals("*"))
                .toList());
        availableResources.remove(simulation.getRovers().get(0).getCurrentPosition());
        return  availableResources;
    }

    public void placeAllRovers(){
        List<Coordinate> resourcesSpot = getResourcesLocatedNearCommandCenter();
        String[][] mapRepresentation = simulation.getMap().getRepresentation();
        for (Coordinate coordinate : resourcesSpot) {
            Map<String, Set<Coordinate>> resourcesLocation = new HashMap<>();
            simulation.addToRovers(new Rover(coordinate, 1, resourcesLocation, calculator));
            if (mapRepresentation[coordinate.x()][coordinate.y()].equals("%")) {
                commandCenter.addResourcesToCommandCenter("%", coordinate);
                String roverName = simulation.getRovers().get(simulation.getRovers().size() - 1).getName();
                consoleLogger.log(roverName + " extracted a mineral at coordinate " + coordinate);
                fileLogger.log(roverName + " extracted a mineral at coordinate " + coordinate);
                consoleLogger.log("The resource has been brought to the command center!");
                fileLogger.log("The resource has been brought to the command center!");
                System.out.println("\n");
            } else {
                commandCenter.addResourcesToCommandCenter("*", coordinate);
                String roverName = simulation.getRovers().get(simulation.getRovers().size() - 1).getName();
                consoleLogger.log(roverName + " extracted a pocket of water at coordinate " + coordinate);
                fileLogger.log(roverName + " extracted a pocket of water at coordinate " + coordinate);
                consoleLogger.log("The resource has been brought to the command center!");
                fileLogger.log("The resource has been brought to the command center!");
                System.out.println("\n");
            }
        }
        consoleLogger.log("All the resources near command center have been exhausted!");
        fileLogger.log("All the resources near command center have been exhausted!");
        System.out.println("\n");
    }

    public void setCommandCenter(CommandCenter commandCenter) {
        this.commandCenter = commandCenter;
    }
}

