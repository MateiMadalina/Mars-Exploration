package com.codecool.marsexploration.mapexplorer.routines;

import com.codecool.marsexploration.mapexplorer.calculators.CoordinateCalculator;
import com.codecool.marsexploration.mapexplorer.calculators.CoordinateCalculatorImpl;
import com.codecool.marsexploration.mapexplorer.commandCenter.CommandCenter;
import com.codecool.marsexploration.mapexplorer.commandCenter.Status;
import com.codecool.marsexploration.mapexplorer.logger.ConsoleLogger;
import com.codecool.marsexploration.mapexplorer.logger.FileLogger;
import com.codecool.marsexploration.mapexplorer.logger.Logger;
import com.codecool.marsexploration.mapexplorer.map.MapLoader;
import com.codecool.marsexploration.mapexplorer.map.Coordinate;
import com.codecool.marsexploration.mapexplorer.rovers.Rover;
import com.codecool.marsexploration.mapexplorer.simulation.Simulation;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.*;

public class ExtractionRoutineTest {
    String mapFile = "src/main/resources/exploration-0.map";
    Map<String, Set<Coordinate>> resourceLocations = Map.of("%",
            Set.of(new Coordinate(1, 14),
                    new Coordinate(6, 15),
                    new Coordinate(16, 4))
    );
    List<String> symbols = List.of("#", "&", "%", "*");
    Logger consoleLogger = new ConsoleLogger();
    Logger fileLogger = new FileLogger("src/main/resources/exploration-outcome.txt");


    CoordinateCalculator calculator = new CoordinateCalculatorImpl(MapLoader.load(mapFile,32,32));
    Rover rover = new Rover( new Coordinate(1, 13), 1, resourceLocations, calculator);

    List<Rover> rovers = new ArrayList<>(List.of(rover));

    Map<String, List<Coordinate>> resourcesOnStock = new HashMap<>();
    CommandCenter commandCenter = new CommandCenter(new Coordinate(1, 12), Status.BUILT, resourcesOnStock);

    Simulation simulation = new Simulation(300, rovers, new Coordinate(3, 11), MapLoader.load(mapFile,32,32), symbols);
    ExtractionRoutine extractionRoutine = new ExtractionRoutine(simulation, calculator, consoleLogger, fileLogger, commandCenter);

    ExtractionRoutineTest() throws FileNotFoundException {
    }

    @Test
    void run() {
        extractionRoutine.run();
    }
}
