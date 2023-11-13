package com.codecool.marsexploration.mapexplorer.maploader;

import com.codecool.marsexploration.mapexplorer.service.input.MapFileReader;
import com.codecool.marsexploration.mapexplorer.maploader.model.MapModel;

import java.io.FileNotFoundException;

public class MapLoaderImpl implements MapLoader{
    private final MapFileReader mapFileReader;

    public MapLoaderImpl(MapFileReader mapFileReader) {
        this.mapFileReader = mapFileReader;
    }

    @Override
    public MapModel load(String mapFile) throws FileNotFoundException {
        String mapString = mapFileReader.readMapFile(mapFile);
        String[][] mapRepresentation = populate2DArray(32, 32, mapString);
        return new MapModel(mapRepresentation);
    }

    private static String[][] populate2DArray(int rows, int columns, String dataString) {
        String EMPTY_SPACE = " ";
        String[] dataArray = dataString.split("(?<=.)"); // Split into individual characters
        String[][] newArray = new String[rows][columns];
        int dataIndex = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (dataIndex < dataArray.length) {
                    String symbol = dataArray[dataIndex].trim();
                    newArray[i][j] = symbol.equals("") ? EMPTY_SPACE : symbol;
                    dataIndex++;
                } else {
                    newArray[i][j] = EMPTY_SPACE;
                }
            }
        }
        return newArray;
    }
}
