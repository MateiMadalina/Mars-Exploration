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
        int expectedMountainNumber = 70;
        int expectedPitNumber = 17;
        int expectedMineralNumber = 10;
        int expectedWaterNumber = 10;

        Map generatedMap = mapLoader.load(mapFile);
        String stringMap = generatedMap.toString();

        int mountainCount = stringMap.length() - stringMap.replaceAll("#", "").length();
        int pitCount = stringMap.length() - stringMap.replaceAll("&", "").length();
        int mineralsCount = stringMap.length() - stringMap.replaceAll("\\*", "").length();
        int waterCount = stringMap.length() - stringMap.replaceAll("%", "").length();

        assertEquals(expectedMountainNumber, mountainCount);
        assertEquals(expectedPitNumber, pitCount);
        assertEquals(expectedMineralNumber, mineralsCount);
        assertEquals(expectedWaterNumber, waterCount);
    }

    @Test
    void loadGeneratesCorrectMapRepresentation() throws FileNotFoundException {
        String expectedMapString = mapFileReader.readMapFile(mapFile);
        String actualMapString = mapLoader.load(mapFile).toString().replaceAll("\n", "");

        assertEquals(expectedMapString, actualMapString);
    }

}