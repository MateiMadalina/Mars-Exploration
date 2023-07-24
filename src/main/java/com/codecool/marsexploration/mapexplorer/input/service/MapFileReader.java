package com.codecool.marsexploration.mapexplorer.input.service;

import com.codecool.marsexploration.mapexplorer.maploader.model.Map;

import java.io.FileNotFoundException;

public interface MapFileReader {
    String readMapFile(String mapFile) throws FileNotFoundException;
}
