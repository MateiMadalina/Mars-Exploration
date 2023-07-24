package com.codecool.marsexploration.mapexplorer.calculators.service;

import com.codecool.marsexploration.mapexplorer.input.service.MapFileReader;
import com.codecool.marsexploration.mapexplorer.input.service.MapFileReaderImpl;
import com.codecool.marsexploration.mapexplorer.maploader.MapLoader;
import com.codecool.marsexploration.mapexplorer.maploader.MapLoaderImpl;
import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CoordinateCalculatorImplTest {
    private final String mapFile = "src/main/resources/exploration-0.map";
    private final MapFileReader mapFileReader = new MapFileReaderImpl();
    private final MapLoader mapLoader = new MapLoaderImpl(mapFileReader);

    @Test
    void getRandomLandingCoordinate() {

    }

    @Test
    void getAdjacentCoordinatesReturns8Coordinates() throws FileNotFoundException {
        Coordinate coordinate = new Coordinate(6, 6);
        CoordinateCalculator coordinateCalculator = new CoordinateCalculatorImpl(mapLoader.load(mapFile));
        List<Coordinate> coordinateList = (List<Coordinate>) coordinateCalculator.getAdjacentCoordinates(coordinate, 1);

        assertEquals(8, coordinateList.size());
    }

    @Test
    void getAdjacentCoordinatesReturns3Coordinates() throws FileNotFoundException {
        Coordinate coordinate = new Coordinate(0, 0);
        CoordinateCalculator coordinateCalculator = new CoordinateCalculatorImpl(mapLoader.load(mapFile));
        List<Coordinate> coordinateList = (List<Coordinate>) coordinateCalculator.getAdjacentCoordinates(coordinate, 1);

        assertEquals(3, coordinateList.size());
    }
}