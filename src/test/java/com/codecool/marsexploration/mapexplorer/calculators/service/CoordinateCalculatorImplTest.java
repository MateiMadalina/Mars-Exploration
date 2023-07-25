package com.codecool.marsexploration.mapexplorer.calculators.service;

import com.codecool.marsexploration.mapexplorer.input.service.MapFileReader;
import com.codecool.marsexploration.mapexplorer.input.service.MapFileReaderImpl;
import com.codecool.marsexploration.mapexplorer.maploader.MapLoader;
import com.codecool.marsexploration.mapexplorer.maploader.MapLoaderImpl;
import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;
import com.codecool.marsexploration.mapexplorer.maploader.model.Map;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CoordinateCalculatorImplTest {
    private final String mapFile = "src/main/resources/exploration-0.map";
    private final MapFileReader mapFileReader = new MapFileReaderImpl();
    private final MapLoader mapLoader = new MapLoaderImpl(mapFileReader);

    @Test
    void getRandomLandingCoordinatePicksEmptySpaceCoordinate() throws FileNotFoundException {
        MapLoader fakeMapLoader = mock(MapLoaderImpl.class);

        String[][] fakeMapRepresentation = {
                {" ", "#", "&"},
                {" ", "*", "%"},
                {"#", " ", "#"}
        };

        Map fakeMap = new Map(fakeMapRepresentation, true);

        when(fakeMapLoader.load(mapFile)).thenReturn(fakeMap);

        CoordinateCalculator coordinateCalculator = new CoordinateCalculatorImpl(fakeMapLoader.load(mapFile));

        List<Coordinate> emptyCoordinates = List.of(new Coordinate(0, 0), new Coordinate(1, 0), new Coordinate(2, 1));
        Coordinate selectedCoordinate = coordinateCalculator.getRandomLandingCoordinate(fakeMapLoader.load(mapFile));

        assertTrue(emptyCoordinates.stream().anyMatch(coordinate -> coordinate.equals(selectedCoordinate)));
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

    @Test
    void getAdjacentCoordinateReturns3Coordinates() throws FileNotFoundException {
        Coordinate coordinate = new Coordinate(32, 32);
        CoordinateCalculator coordinateCalculator = new CoordinateCalculatorImpl(mapLoader.load(mapFile));
        List<Coordinate> coordinateList = (List<Coordinate>) coordinateCalculator.getAdjacentCoordinates(coordinate, 1);
        System.out.println(coordinateList);

        assertEquals(3, coordinateList.size());
    }
}