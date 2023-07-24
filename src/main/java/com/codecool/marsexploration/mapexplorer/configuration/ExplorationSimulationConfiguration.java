package com.codecool.marsexploration.mapexplorer.configuration;

import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;

import java.util.List;

public record ExplorationSimulationConfiguration(
        String mapFile,
        Coordinate landingSpot,
        List<String> symbols,
        int simulationStepsAmount
) { }
