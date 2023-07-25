package com.codecool.marsexploration.mapexplorer.calculators.service;

import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;
import com.codecool.marsexploration.mapexplorer.maploader.model.Map;

public interface CoordinateCalculator {
//    Coordinate getRandomLandingCoordinate(int dimension);

    Coordinate getRandomLandingCoordinate(Map map);
    Iterable<Coordinate> getAdjacentCoordinates(Coordinate coordinate, int dimension);
}
