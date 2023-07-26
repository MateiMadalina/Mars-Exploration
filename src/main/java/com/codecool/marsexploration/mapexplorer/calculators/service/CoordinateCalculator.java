package com.codecool.marsexploration.mapexplorer.calculators.service;

import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;
import com.codecool.marsexploration.mapexplorer.maploader.model.MapModel;

import java.util.List;

public interface CoordinateCalculator {
//    Coordinate getRandomLandingCoordinate(int dimension);

    Coordinate getRandomLandingCoordinate(MapModel map);
    Iterable<Coordinate> getAdjacentCoordinates(Coordinate coordinate, int dimension);
    List<Coordinate> getAllPossiblePlacementsForSpaceshipWithEmptySpaceAdjacent (List<Coordinate> emptySpaces);
    Iterable<Coordinate> getAdjacentCoordinatesBasedOnSight(Coordinate coordinate, int sight);
    List<Coordinate> gatAllPossiblePlacementsForRoverWithEmptySpaceAdjacent(List<Coordinate> spaces);
}
