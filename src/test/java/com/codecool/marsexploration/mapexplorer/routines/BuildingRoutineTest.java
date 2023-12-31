package com.codecool.marsexploration.mapexplorer.routines;

import com.codecool.marsexploration.mapexplorer.calculators.CoordinateCalculator;
import com.codecool.marsexploration.mapexplorer.calculators.CoordinateCalculatorImpl;
import com.codecool.marsexploration.mapexplorer.logger.ConsoleLogger;
import com.codecool.marsexploration.mapexplorer.logger.FileLogger;
import com.codecool.marsexploration.mapexplorer.logger.Logger;
import com.codecool.marsexploration.mapexplorer.map.MapLoader;
import com.codecool.marsexploration.mapexplorer.map.Coordinate;
import com.codecool.marsexploration.mapexplorer.map.MapModel;
import com.codecool.marsexploration.mapexplorer.models.rovers.Rover;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BuildingRoutineTest {
    String mapFile = "src/main/resources/exploration-0.map";
    Map<String, Set<Coordinate>> resourceLocations = Map.of("%",
            Set.of(new Coordinate(1, 14),
                    new Coordinate(6, 15),
                    new Coordinate(16, 4))
    );
    Logger consoleLogger = new ConsoleLogger();
    Logger fileLogger = new FileLogger("src/main/resources/exploration-outcome.txt");
    MapModel currentMap = MapLoader.load(mapFile,32,32);
    CoordinateCalculator calculator = new CoordinateCalculatorImpl(currentMap);
    Rover rover = new Rover( new Coordinate(1, 14), 1, resourceLocations, calculator);
    BuildingRoutine buildingRoutine = new BuildingRoutine(calculator, rover, null, consoleLogger, fileLogger);

    BuildingRoutineTest() throws FileNotFoundException {

    }

    @Test
    void placeCommandCenter() {
        buildingRoutine.placeCommandCenter();
    }

    @Test
    void getRandomResources() {
        Coordinate randCoordinate = buildingRoutine.getRandomResources();
        assertTrue(resourceLocations.get("%").stream().anyMatch(coordinate -> coordinate.equals(randCoordinate)));
    }

    @Test
    void run() {
        buildingRoutine.run();
    }


}
