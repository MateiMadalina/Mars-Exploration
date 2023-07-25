package com.codecool.marsexploration.mapexplorer.maploader;

import com.codecool.marsexploration.mapexplorer.maploader.model.MapModel;

import java.io.FileNotFoundException;

public interface MapLoader {
    MapModel load(String mapFile) throws FileNotFoundException;
}
