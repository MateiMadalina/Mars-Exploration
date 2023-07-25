package com.codecool.marsexploration.mapexplorer.calculators.service;

import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;
import com.codecool.marsexploration.mapexplorer.maploader.model.Map;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CoordinateCalculatorImpl implements CoordinateCalculator {
    private Map map;

    public CoordinateCalculatorImpl(Map map) {
        this.map = map;
    }

    public Coordinate getRandomLandingCoordinate(Map map) {
        Random random = new Random();
        List<Coordinate> emptySpaces = getEmptySpacesCoordinates(map);
        return emptySpaces.get(random.nextInt(emptySpaces.size()));
    }

    private List<Coordinate> getEmptySpacesCoordinates(Map map) {
        String[][] mapRepresentation = map.getRepresentation();
        List<Coordinate> emptySpaces = new ArrayList<>();

        for (int i = 0; i < mapRepresentation.length; i++) {
            for (int j = 0; j < mapRepresentation[i].length; j++) {
                if (mapRepresentation[i][j].equals(" ")) {
                    emptySpaces.add(new Coordinate(i, j));
                }
            }
        }
        return emptySpaces;
    }

    public Iterable<Coordinate> getAdjacentCoordinates(Coordinate coordinate, int dimension) {
        int xStart = coordinate.x() > 0 ? coordinate.x() - 1 : coordinate.x();
        int yStart = coordinate.y() > 0 ? coordinate.y() - 1 : coordinate.y();
        int xFinal = xStart == map.getDimension() - 1 ? xStart : xStart > 0 ? xStart + dimension  : xStart ;
        int yFinal = yStart == map.getDimension() - 1 ? yStart : yStart > 0 ? yStart + dimension  : yStart ;

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
