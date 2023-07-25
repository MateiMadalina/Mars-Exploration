package com.codecool.marsexploration.mapexplorer;

import com.codecool.marsexploration.mapexplorer.input.service.MapFileReader;
import com.codecool.marsexploration.mapexplorer.input.service.MapFileReaderImpl;
import com.codecool.marsexploration.mapexplorer.maploader.MapLoader;
import com.codecool.marsexploration.mapexplorer.maploader.MapLoaderImpl;
import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;
import com.codecool.marsexploration.mapexplorer.maploader.model.MapModel;

import java.io.FileNotFoundException;

public class Application {
    private static final String workDir = "src/main";

    public static void main(String[] args) throws FileNotFoundException {
        String mapFile = workDir + "/resources/exploration-0.map";
        Coordinate landingSpot = new Coordinate(6, 6);

        MapFileReader mapFileReader = new MapFileReaderImpl();

        MapLoader mapLoader = new MapLoaderImpl(mapFileReader);
        MapModel newMap = mapLoader.load(mapFile);
        System.out.println(newMap.toString());






        // Add your code here
    }
}

