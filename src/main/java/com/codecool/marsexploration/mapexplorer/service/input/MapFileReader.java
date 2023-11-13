package com.codecool.marsexploration.mapexplorer.service.input;

import java.io.FileNotFoundException;

public interface MapFileReader {
    String readMapFile(String mapFile) throws FileNotFoundException;
}
