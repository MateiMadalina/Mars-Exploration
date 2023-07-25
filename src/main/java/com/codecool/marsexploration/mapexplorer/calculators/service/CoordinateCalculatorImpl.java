package com.codecool.marsexploration.mapexplorer.calculators.service;

import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;
import com.codecool.marsexploration.mapexplorer.maploader.model.MapModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CoordinateCalculatorImpl implements CoordinateCalculator {
    private MapModel map;

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

    private List<Coordinate> getAllPossiblePlacementsForSpaceshipWithEmptySpaceAdjacent (List<Coordinate> emptySpaces){
        List<Coordinate> possibleSpots = new ArrayList<>();

        for (Coordinate emptySpace : emptySpaces) {
            List<Coordinate> adjacentCoordinates = (List<Coordinate>) getAdjacentCoordinates(emptySpace,1);
            if (adjacentCoordinates.stream().anyMatch(coordinate -> {
                String content = map.getRepresentation()[coordinate.x()][coordinate.y()];
                return content.equals(" ");
            })) {
                possibleSpots.add(emptySpace);
            }
        }
        return possibleSpots;
    }

    public Iterable<Coordinate> getAdjacentCoordinates(Coordinate coordinate, int dimension) {
        int xStart = coordinate.x() > 0 ? coordinate.x() - 1 : coordinate.x();
        int yStart = coordinate.y() > 0 ? coordinate.y() - 1 : coordinate.y();
        int xFinal = xStart == map.getDimension() - 2 ? xStart + dimension : xStart > 0 ? xStart + dimension + 1  : xStart + dimension ;
        int yFinal = yStart == map.getDimension() - 2 ? yStart + dimension : yStart > 0 ? yStart + dimension + 1  : yStart + dimension;

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


//    public List<Coordinate> getAdjacentCoordinates(Coordinate coordinate,int dimension) {
//        int x = coordinate.x();
//        int y = coordinate.y();
//
//        int xStart = Math.max(0, x - dimension);
//        int yStart = Math.max(0, y - dimension);
//        int xFinal = Math.min(map.getDimension() - 1, x + dimension);
//        int yFinal = Math.min(map.getDimension() - 1, y + dimension);
//
//        List<Coordinate> allCoordinates = new ArrayList<>();
//        for (int i = xStart; i <= xFinal; i++) {
//            for (int j = yStart; j <= yFinal; j++) {
//                if (i != x || j != y) {
//                    allCoordinates.add(new Coordinate(i, j));
//                }
//            }
//        }
//        return allCoordinates;
//    }
}
