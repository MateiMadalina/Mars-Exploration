package com.codecool.marsexploration.mapexplorer.rovers;

import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;

import java.util.List;
import java.util.Map;

public class Rover {
    private int id;
    private String name;
    private Coordinate currentPosition;
    private int sight;
    private Map<String, List<Coordinate>> resourceLocations;

    public Rover(int id, String name, Coordinate currentPosition, int sight, Map<String, List<Coordinate>> resourceLocations) {
        id = id;
        this.name = name;
        this.currentPosition = currentPosition;
        this.sight = sight;
        this.resourceLocations = resourceLocations;
    }
}
