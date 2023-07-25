package com.codecool.marsexploration.mapexplorer.input.service;

import java.io.FileNotFoundException;

public interface MapFileReader {
    String readMapFile(String mapFile) throws FileNotFoundException;
}
