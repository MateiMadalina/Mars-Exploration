package com.codecool.marsexploration.mapexplorer.configuration;

import com.codecool.marsexploration.mapexplorer.calculators.CoordinateCalculator;
import com.codecool.marsexploration.mapexplorer.calculators.CoordinateCalculatorImpl;
import com.codecool.marsexploration.mapexplorer.map.MapLoader;
import com.codecool.marsexploration.mapexplorer.map.Coordinate;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ConfigurationValidatorImplTest {
    private final String mapFile = "src/main/resources/exploration-0.map";
    private final List<String> symbols = List.of("#", "&", "*", "%");

    @Test
    void validateReturnsTrueForLandingOnEmptySpace() throws FileNotFoundException {
        Coordinate coordinate = new Coordinate(0, 0);

        ExplorationSimulationConfiguration explorationConfig = new ExplorationSimulationConfiguration(
                mapFile, coordinate, symbols, 30);
        CoordinateCalculator coordinateCalculator = new CoordinateCalculatorImpl(MapLoader.load(mapFile,32,32));
        ConfigurationValidator configurationValidator = new ConfigurationValidatorImpl(coordinateCalculator,explorationConfig);

        assertTrue(configurationValidator.validate(MapLoader.load(mapFile,32,32)));
    }

    @Test
    void validateReturnsFalseForLandingOnOccupiedSpace() throws FileNotFoundException {
        Coordinate coordinate = new Coordinate(2, 14);
        ExplorationSimulationConfiguration explorationConfig = new ExplorationSimulationConfiguration(
                mapFile, coordinate, symbols, 30);

        CoordinateCalculator coordinateCalculator = new CoordinateCalculatorImpl(MapLoader.load(mapFile,32,32));
        ConfigurationValidator configurationValidator = new ConfigurationValidatorImpl(coordinateCalculator,explorationConfig);

        assertFalse(configurationValidator.validate(MapLoader.load(mapFile,32,32)));
    }

    @Test
    void validateReturnsFalseForNoAvailableSpaceAroundShip() throws FileNotFoundException {
        Coordinate coordinate = new Coordinate(16, 26);
        ExplorationSimulationConfiguration explorationConfig = new ExplorationSimulationConfiguration(
                mapFile, coordinate, symbols, 30);
        CoordinateCalculator coordinateCalculator = new CoordinateCalculatorImpl(MapLoader.load(mapFile,32,32));
        ConfigurationValidator configurationValidator = new ConfigurationValidatorImpl(coordinateCalculator,explorationConfig);

        assertFalse(configurationValidator.validate(MapLoader.load(mapFile,32,32)));
    }

    @Test
    void validateReturnsTrueForAvailableSpaceAroundShip() throws FileNotFoundException {
        Coordinate coordinate = new Coordinate(16, 28);
        ExplorationSimulationConfiguration explorationConfig = new ExplorationSimulationConfiguration(
                mapFile, coordinate, symbols, 30);
        CoordinateCalculator coordinateCalculator = new CoordinateCalculatorImpl(MapLoader.load(mapFile,32,32));
        ConfigurationValidator configurationValidator = new ConfigurationValidatorImpl(coordinateCalculator,explorationConfig);

        assertTrue(configurationValidator.validate(MapLoader.load(mapFile,32,32)));
    }

    @Test
    void emptyMapReturnsFalse() throws FileNotFoundException {
        Coordinate coordinate = new Coordinate(16, 28);
        String emptyMapFile = "src/main/resources/exploration-empty.map";
        ExplorationSimulationConfiguration explorationConfig = new ExplorationSimulationConfiguration(
                emptyMapFile, coordinate, symbols, 30);
        CoordinateCalculator coordinateCalculator = new CoordinateCalculatorImpl(MapLoader.load(emptyMapFile,32,32));
        ConfigurationValidator configurationValidator = new ConfigurationValidatorImpl(coordinateCalculator,explorationConfig);

        assertFalse(configurationValidator.validate(MapLoader.load(emptyMapFile,32,32)));
    }

    @Test
    void emptyMapReturnsFalseForStepsUnder0() throws FileNotFoundException {
            Coordinate coordinate = new Coordinate(16, 28);
            ExplorationSimulationConfiguration explorationConfig = new ExplorationSimulationConfiguration(
                    mapFile, coordinate, symbols, -3);
            CoordinateCalculator coordinateCalculator = new CoordinateCalculatorImpl(MapLoader.load(mapFile,32,32));
            ConfigurationValidator configurationValidator = new ConfigurationValidatorImpl(coordinateCalculator,explorationConfig);

            assertFalse(configurationValidator.validate(MapLoader.load(mapFile,32,32)));
        }
}