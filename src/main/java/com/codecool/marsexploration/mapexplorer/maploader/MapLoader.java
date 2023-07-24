package com.codecool.marsexploration.mapexplorer.maploader;

import com.codecool.marsexploration.mapexplorer.maploader.model.Map;

import java.io.FileNotFoundException;

public interface MapLoader {
    Map load(String mapFile) throws FileNotFoundException;
}
