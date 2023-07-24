package com.codecool.marsexploration.mapexplorer.configuration;

import com.codecool.marsexploration.mapexplorer.calculators.service.CoordinateCalculator;
import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;
import com.codecool.marsexploration.mapexplorer.maploader.model.Map;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ConfigurationValidatorImpl implements ConfigurationValidator{
    private CoordinateCalculator coordinateCalculator;
    private ExplorationSimulationConfiguration explorationConfig;

    public ConfigurationValidatorImpl(CoordinateCalculator coordinateCalculator, ExplorationSimulationConfiguration explorationConfig) {
        this.coordinateCalculator = coordinateCalculator;
        this.explorationConfig = explorationConfig;
    }

    @Override
    public boolean validate(Map map, Coordinate coordinate) {
        return landingSpotIsAvailable(map, coordinate)
                && availableSpotNextToShip(map, coordinate)
                && mapFileNotEmpty(map)
                && atLeastOneResourceSpecifiedInSymbolList()
                && stepsAmountGreaterThanZero();
    }

    private boolean landingSpotIsAvailable(Map map, Coordinate coordinate) {
        String[][] mapRepresentation = map.getRepresentation();
        return Objects.equals(mapRepresentation[coordinate.x()][coordinate.y()], " ");
    }

    private boolean availableSpotNextToShip(Map map, Coordinate coordinate) {
        String[][] mapRepresentation = map.getRepresentation();
        List<Coordinate> allCoordinates = (List<Coordinate>) coordinateCalculator.getAdjacentCoordinates(coordinate, 1);

       return allCoordinates.stream().anyMatch(coord -> mapRepresentation[coord.x()][coord.y()].equals(" "));
    }

    private boolean mapFileNotEmpty (Map map) {
        String mapString = map.toString().trim();
        return !mapString.isEmpty();
    }

    private boolean atLeastOneResourceSpecifiedInSymbolList() {
        List<String> symbols = explorationConfig.symbols();

        return symbols.stream().anyMatch(
                symbol -> symbol.equals("#")
                        || symbol.equals("&")
                        || symbol.equals("*")
                        || symbol.equals("%"));
    }

    private boolean stepsAmountGreaterThanZero() {
        return explorationConfig.simulationStepsAmount() > 0;
    }
}
