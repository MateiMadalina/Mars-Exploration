package com.codecool.marsexploration.mapexplorer.simulation;

import com.codecool.marsexploration.mapexplorer.calculators.service.CoordinateCalculator;
import com.codecool.marsexploration.mapexplorer.expeditionDeployer.MapExpeditionDeployer;
import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;
import com.codecool.marsexploration.mapexplorer.rovers.Rover;

import java.util.Random;

public class SimulationSteps {
    private final CoordinateCalculator calculator;
    private final Simulation simulation;
    private final Random rand;
    private final MapExpeditionDeployer deployer;

    public SimulationSteps(CoordinateCalculator calculator, Simulation simulation, MapExpeditionDeployer deployer) {
        this.calculator = calculator;
        this.simulation = simulation;
        this.deployer = deployer;
        this.rand = new Random();
    }

    public void roverMovementRoutine(){
        deployer.sendExpedition();
        simulation.getRover().addToPickedSteps(simulation.getRover().getCurrentPosition());
        simulation.getRover().addToResourceMap(simulation.getMap(), simulation.getSymbols());
        int count = 0;
//        while (simulation.getRover().getPickedSteps().size() <= simulation.getStepsToTimeout()){
//            Coordinate pickedCoordinate = simulation.getRover().pickStep();
//            System.out.println(count);
//            while (simulation.getRover().getPickedSteps().contains(pickedCoordinate)){
//                count++;
//                pickedCoordinate = simulation.getRover().pickStep();
//                if (count > simulation.getStepsToTimeout()){
//                    System.out.println("here");
//                    simulation.getRover().returnToSpaceship(simulation.getSpaceShip());
//                    break;
//                }
//            }
//            simulation.getRover().addToPickedSteps(pickedCoordinate);
//            simulation.getRover().setCurrentPosition(pickedCoordinate);
//            simulation.getRover().addToResourceMap(simulation.getMap(), simulation.getSymbols());
//        }
        for (int i = 0; i < simulation.getStepsToTimeout(); i++) {
            Coordinate pickedCoordinate = simulation.getRover().pickStep();
            while (simulation.getRover().getPickedSteps().contains(pickedCoordinate)) {
                count++;
                pickedCoordinate = simulation.getRover().pickStep();
                if (count > simulation.getStepsToTimeout()) {
                    i = simulation.getStepsToTimeout();
                    break;
                }
            }
            simulation.getRover().addToPickedSteps(pickedCoordinate);
            simulation.getRover().setCurrentPosition(pickedCoordinate);
            simulation.getRover().addToResourceMap(simulation.getMap(), simulation.getSymbols());
        }
        simulation.getRover().returnToSpaceship(simulation.getSpaceShip());
        System.out.println(simulation.getRover().getCurrentPosition());
        System.out.println(simulation.getRover().getResourceLocations());
    }
}
