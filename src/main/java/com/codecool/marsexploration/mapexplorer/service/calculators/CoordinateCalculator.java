package com.codecool.marsexploration.mapexplorer.service.calculators;

import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;
import com.codecool.marsexploration.mapexplorer.maploader.model.MapModel;

import java.util.List;

public interface CoordinateCalculator {
    Coordinate getRandomLandingCoordinate(MapModel map);
    Iterable<Coordinate> getAdjacentCoordinates(Coordinate coordinate, int dimension);
    List<Coordinate> getAllPossiblePlacementsForSpaceshipWithEmptySpaceAdjacent (List<Coordinate> emptySpaces);
    List<Coordinate> gatAllPossiblePlacementsForRoverWithEmptySpaceAdjacent(List<Coordinate> spaces);
}
