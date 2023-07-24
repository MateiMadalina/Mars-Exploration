package com.codecool.marsexploration.mapexplorer.calculators.service;

import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;

public interface CoordinateCalculator {
    Coordinate getRandomLandingCoordinate(int dimension);
    Iterable<Coordinate> getAdjacentCoordinates(Coordinate coordinate, int dimension);
}
