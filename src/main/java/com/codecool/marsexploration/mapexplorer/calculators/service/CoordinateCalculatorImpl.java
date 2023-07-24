package com.codecool.marsexploration.mapexplorer.calculators.service;

import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;
import com.codecool.marsexploration.mapexplorer.maploader.model.Map;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CoordinateCalculatorImpl implements CoordinateCalculator {
    private Map map;

    public CoordinateCalculatorImpl(Map map) {
        this.map = map;
    }

    public Coordinate getRandomLandingCoordinate(int dimension) {
        Random random = new Random();
        int mapDimension = map.getDimension();
        return new Coordinate(random.nextInt(mapDimension - dimension),
                random.nextInt(mapDimension - dimension));
    }

    public Iterable<Coordinate> getAdjacentCoordinates(Coordinate coordinate, int dimension) {
        int xStart = coordinate.x() > 0 ? coordinate.x() - 1 : coordinate.x();
        int yStart = coordinate.y() > 0 ? coordinate.y() - 1 : coordinate.y();
        int xFinal = xStart > 0 ? xStart + dimension  + 1 : xStart + dimension;
        int yFinal = yStart > 0 ? yStart + dimension  + 1 : yStart + dimension;

        List<Coordinate> allCoordinates = (List<Coordinate>) getCoordinates(xStart, yStart, xFinal, yFinal);
        allCoordinates.remove(coordinate);
        return allCoordinates;
    }

    private Iterable<Coordinate> getCoordinates(int xStart, int yStart, int xFinal, int yFinal) {
        return IntStream.rangeClosed(xStart, xFinal)
                .boxed()
                .flatMap(x -> IntStream.rangeClosed(yStart, yFinal)
                        .mapToObj(y -> new Coordinate(x, y)))
                .collect(Collectors.toList());
    }
}
