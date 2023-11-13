package com.codecool.marsexploration.mapexplorer.map;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MapLoader {
    private static final String EMPTY_SPACE = " ";

    public static MapModel load(String filePath, int rows, int columns) throws FileNotFoundException {
        String mapString = readMapFile(filePath);
        String[][] mapRepresentation = populate2DArray(rows, columns, mapString);
        return new MapModel(mapRepresentation);
    }

    public static String readMapFile(String filePath) throws FileNotFoundException {
        StringBuilder characters = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                characters.append(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return characters.toString();
    }

    private static String[][] populate2DArray(int rows, int columns, String dataString) {
        String[] dataArray = dataString.split("(?<=.)"); // Split into individual characters
        String[][] newArray = new String[rows][columns];
        int dataIndex = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (dataIndex < dataArray.length) {
                    String symbol = dataArray[dataIndex];
                    newArray[i][j] = symbol.isEmpty() ? EMPTY_SPACE : symbol;
                    dataIndex++;
                } else {
                    newArray[i][j] = EMPTY_SPACE;
                }
            }
        }
        return newArray;
    }
}
