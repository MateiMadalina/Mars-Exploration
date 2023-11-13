package com.codecool.marsexploration.mapexplorer.calculators;

import com.codecool.marsexploration.mapexplorer.map.Coordinate;
import com.codecool.marsexploration.mapexplorer.map.MapModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CoordinateCalculatorImpl implements CoordinateCalculator {
    private final MapModel map;

    public CoordinateCalculatorImpl(MapModel map) {
        this.map = map;
    }

    public Coordinate getRandomLandingCoordinate(MapModel map) {
        Random random = new Random();
        List<Coordinate> emptySpaces = getEmptySpacesCoordinates(map);
        List<Coordinate> possibleSpots = getAllPossiblePlacementsForSpaceshipWithEmptySpaceAdjacent(emptySpaces);
        return possibleSpots.get(random.nextInt(possibleSpots.size()));
    }

    private List<Coordinate> getEmptySpacesCoordinates(MapModel map) {
        String[][] mapRepresentation = map.representation();
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

    public List<Coordinate> getAllPossiblePlacementsForSpaceshipWithEmptySpaceAdjacent(List<Coordinate> emptySpaces) {
        List<Coordinate> possibleSpots = new ArrayList<>();

        for (Coordinate emptySpace : emptySpaces) {
            List<Coordinate> adjacentCoordinates = (List<Coordinate>) getAdjacentCoordinates(emptySpace, 1);
            if (adjacentCoordinates.stream().anyMatch(coordinate -> {
                String content = map.representation()[coordinate.x()][coordinate.y()];
                return content.equals(" ");
            })) {
                possibleSpots.add(emptySpace);
            }
        }
        return possibleSpots;
    }

    public List<Coordinate> gatAllPossiblePlacementsForRoverWithEmptySpaceAdjacent(List<Coordinate> spaces) {
        return spaces.stream()
                .filter(coordinate -> map.representation()[coordinate.x()][coordinate.y()].equals(" "))
                .toList();
    }

    public Iterable<Coordinate> getAdjacentCoordinates(Coordinate coordinate, int dimension) {
        int mapDimension = map.getDimension() - 1;
        int xStart = Math.max(coordinate.x() - dimension, 0);
        int yStart = Math.max(coordinate.y() - dimension, 0);
        int xFinal = Math.min(coordinate.x() + dimension, mapDimension);
        int yFinal = Math.min(coordinate.y() + dimension, mapDimension);

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
