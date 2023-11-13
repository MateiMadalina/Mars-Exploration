package com.codecool.marsexploration.mapexplorer.configuration;

import com.codecool.marsexploration.mapexplorer.calculators.CoordinateCalculator;
import com.codecool.marsexploration.mapexplorer.map.Coordinate;
import com.codecool.marsexploration.mapexplorer.map.MapModel;

import java.util.List;
import java.util.Objects;

public class ConfigurationValidatorImpl implements ConfigurationValidator{
    private final CoordinateCalculator coordinateCalculator;
    private final ExplorationSimulationConfiguration explorationConfig;

    public ConfigurationValidatorImpl(CoordinateCalculator coordinateCalculator, ExplorationSimulationConfiguration explorationConfig) {
        this.coordinateCalculator = coordinateCalculator;
        this.explorationConfig = explorationConfig;
    }

    @Override
    public boolean validate(MapModel map) {
        return landingSpotIsAvailable(map, explorationConfig.landingSpot())
                && availableSpotNextToShip(map, explorationConfig.landingSpot())
                && mapFileNotEmpty(map)
                && atLeastOneResourceSpecifiedInSymbolList()
                && stepsAmountGreaterThanZero();
    }

    private boolean landingSpotIsAvailable(MapModel map, Coordinate coordinate) {
        String[][] mapRepresentation = map.representation();
        return Objects.equals(mapRepresentation[coordinate.x()][coordinate.y()], " ");
    }

    private boolean availableSpotNextToShip(MapModel map, Coordinate coordinate) {
        String[][] mapRepresentation = map.representation();
        List<Coordinate> allCoordinates = (List<Coordinate>) coordinateCalculator.getAdjacentCoordinates(coordinate, 1);

       return allCoordinates.stream().anyMatch(coord -> mapRepresentation[coord.x()][coord.y()].equals(" "));
    }

    private boolean mapFileNotEmpty (MapModel map) {
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
