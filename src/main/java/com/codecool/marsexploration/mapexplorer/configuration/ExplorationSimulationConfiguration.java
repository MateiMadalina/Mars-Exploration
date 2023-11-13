package com.codecool.marsexploration.mapexplorer.configuration;

import com.codecool.marsexploration.mapexplorer.map.Coordinate;

import java.util.List;

public record ExplorationSimulationConfiguration(String mapFile, Coordinate landingSpot, List<String> symbols, int simulationStepsAmount) {

}
