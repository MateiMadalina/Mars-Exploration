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
        while (simulation.getRover().getPickedSteps().size() <= simulation.getStepsToTimeout()){
            Coordinate pickedCoordinate = simulation.getRover().pickStep();
            System.out.println(pickedCoordinate);
            while (!simulation.getRover().getPickedSteps().contains(pickedCoordinate)){
                pickedCoordinate = simulation.getRover().pickStep();

            }
            simulation.getRover().setCurrentPosition(pickedCoordinate);
            simulation.getRover().addToPickedSteps(pickedCoordinate);
            simulation.getRover().addToResourceMap(simulation.getMap(), simulation.getSymbols());
        }
        System.out.println(simulation.getRover().getPickedSteps());
    }
}
