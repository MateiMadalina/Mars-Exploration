package com.codecool.marsexploration.mapexplorer.calculators;

import com.codecool.marsexploration.mapexplorer.map.MapLoader;
import com.codecool.marsexploration.mapexplorer.map.Coordinate;
import com.codecool.marsexploration.mapexplorer.map.MapModel;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.io.FileNotFoundException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CoordinateCalculatorImplTest {
    private final String mapFile = "src/main/resources/exploration-0.map";

    @Test
    void getRandomLandingCoordinatePicksEmptySpaceCoordinate() throws FileNotFoundException {
        String[][] fakeMapRepresentation = {
                {" ", "#", "&"},
                {" ", "*", "%"},
                {"#", " ", "#"}
        };

        MapModel fakeMap = new MapModel(fakeMapRepresentation);

        // Mocking static method
        try (MockedStatic<MapLoader> mapLoaderMockedStatic = mockStatic(MapLoader.class)) {
            mapLoaderMockedStatic.when(() -> MapLoader.load(mapFile, 32, 32)).thenReturn(fakeMap);

            CoordinateCalculator coordinateCalculator = new CoordinateCalculatorImpl(fakeMap);

            List<Coordinate> emptyCoordinates = List.of(new Coordinate(0, 0), new Coordinate(1, 0), new Coordinate(2, 1));
            Coordinate selectedCoordinate = coordinateCalculator.getRandomLandingCoordinate(fakeMap);

            assertTrue(emptyCoordinates.stream().anyMatch(coordinate -> coordinate.equals(selectedCoordinate)));
        }
    }
    @Test
    void getAdjacentCoordinatesReturns8Coordinates() throws FileNotFoundException {
        Coordinate coordinate = new Coordinate(6, 6);
        CoordinateCalculator coordinateCalculator = new CoordinateCalculatorImpl(MapLoader.load(mapFile,32,32));
        List<Coordinate> coordinateList = (List<Coordinate>) coordinateCalculator.getAdjacentCoordinates(coordinate, 1);
        System.out.println(coordinateList);

        assertEquals(8, coordinateList.size());
    }

    @Test
    void getAdjacentCoordinatesReturns3CoordinatesForUpperLeftCorner() throws FileNotFoundException {
        Coordinate coordinate = new Coordinate(0, 0);
        CoordinateCalculator coordinateCalculator = new CoordinateCalculatorImpl(MapLoader.load(mapFile,32,32));
        List<Coordinate> coordinateList = (List<Coordinate>) coordinateCalculator.getAdjacentCoordinates(coordinate, 1);
        System.out.println(coordinateList);

        assertEquals(3, coordinateList.size());
    }

    @Test
    void getAdjacentCoordinatesReturns3CoordinatesForLowerRightCorner() throws FileNotFoundException {
        Coordinate coordinate = new Coordinate(31, 31);
        CoordinateCalculator coordinateCalculator = new CoordinateCalculatorImpl(MapLoader.load(mapFile,32,32));
        List<Coordinate> coordinateList = (List<Coordinate>) coordinateCalculator.getAdjacentCoordinates(coordinate, 1);
        System.out.println(coordinateList);

        assertEquals(3, coordinateList.size());
    }

    @Test
    void getAdjacentCoordinatesReturns3CoordinatesForUpperRightCorner() throws FileNotFoundException {
        Coordinate coordinate = new Coordinate(0, 31);
        CoordinateCalculator coordinateCalculator = new CoordinateCalculatorImpl(MapLoader.load(mapFile,32,32));
        List<Coordinate> coordinateList = (List<Coordinate>) coordinateCalculator.getAdjacentCoordinates(coordinate, 1);
        System.out.println(coordinateList);

        assertEquals(3, coordinateList.size());
    }

    @Test
    void getAdjacentCoordinatesReturns3CoordinatesForLowerLeftCorner() throws FileNotFoundException {
        //extract method
        Coordinate coordinate = new Coordinate(31, 0);
        CoordinateCalculator coordinateCalculator = new CoordinateCalculatorImpl(MapLoader.load(mapFile,32,32));
        List<Coordinate> coordinateList = (List<Coordinate>) coordinateCalculator.getAdjacentCoordinates(coordinate, 1);
        System.out.println(coordinateList);

        assertEquals(3, coordinateList.size());
    }

    @Test
    void getAdjacentCoordinatesReturns3CoordinatesForLowerCoordinate() throws FileNotFoundException {
        Coordinate coordinate = new Coordinate(31, 30);
        CoordinateCalculator coordinateCalculator = new CoordinateCalculatorImpl(MapLoader.load(mapFile,32,32));
        List<Coordinate> coordinateList = (List<Coordinate>) coordinateCalculator.getAdjacentCoordinates(coordinate, 1);
        System.out.println(coordinateList);

        assertEquals(5, coordinateList.size());
    }

    @Test
    void getAdjacentCoordinatesBasedOnSight() throws  FileNotFoundException {
        Coordinate coordinate = new Coordinate(0, 31);
        CoordinateCalculator coordinateCalculator = new CoordinateCalculatorImpl(MapLoader.load(mapFile,32,32));
        List<Coordinate> coordinateList = (List<Coordinate>) coordinateCalculator.getAdjacentCoordinates(coordinate, 1);
        System.out.println(coordinateList);

        assertEquals(3, coordinateList.size());
    }
}