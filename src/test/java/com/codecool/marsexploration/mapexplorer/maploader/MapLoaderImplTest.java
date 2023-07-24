package com.codecool.marsexploration.mapexplorer.maploader;

import com.codecool.marsexploration.mapexplorer.input.service.MapFileReader;
import com.codecool.marsexploration.mapexplorer.input.service.MapFileReaderImpl;
import com.codecool.marsexploration.mapexplorer.maploader.model.Map;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
class MapLoaderImplTest {
    private final MapFileReader mapFileReader = new MapFileReaderImpl();
    private final MapLoader mapLoader = new MapLoaderImpl(mapFileReader);

    private final String mapFile = "src/main/resources/exploration-0.map";

    @Test
    void loadReturns2dMapRepresentationOf32x32() throws FileNotFoundException {
        int expectedRows = 32;
        int expectedColumns = 32;

        Map generatedMap = mapLoader.load(mapFile);
        int actualRows = generatedMap.getRepresentation().length;
        int actualColumns = generatedMap.getRepresentation()[0].length;

        assertEquals(expectedRows, actualRows);
        assertEquals(expectedColumns, actualColumns);
    }

    @Test
    void loadReturnsExpectedAmountsOfSymbols() throws FileNotFoundException {
        int mountains = 70;
        int pits = 17;
        int minerals = 10;
        int water = 10;

        Map generatedMap = mapLoader.load(mapFile);
        String stringMap = generatedMap.toString();

        int mountainCounter = stringMap.length() - stringMap.replaceAll("#", "").length();
        int pitCounter = stringMap.length() - stringMap.replaceAll("&", "").length();
        int mineralsCounter = stringMap.length() - stringMap.replaceAll("\\*", "").length();
        int waterCounter = stringMap.length() - stringMap.replaceAll("%", "").length();

        assertEquals(mountains, mountainCounter);
        assertEquals(pits, pitCounter);
        assertEquals(minerals, mineralsCounter);
        assertEquals(water, waterCounter);
    }

    @Test
    void loadGeneratesCorrectMapRepresentation() throws FileNotFoundException {
        String expectedMapString = mapFileReader.readMapFile(mapFile);
        String actualMapString = mapLoader.load(mapFile).toString().replaceAll("\n", "");

        assertEquals(expectedMapString, actualMapString);
    }

}