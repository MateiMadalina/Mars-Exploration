package com.codecool.marsexploration.mapexplorer.map;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
class MapLoaderImplTest {

    private final String mapFile = "src/main/resources/exploration-0.map";

    @Test
    void loadReturns2dMapRepresentationOf32x32() throws FileNotFoundException {
        int expectedRows = 32;
        int expectedColumns = 32;

        MapModel generatedMap = MapLoader.load(mapFile,32,32);
        int actualRows = generatedMap.representation().length;
        int actualColumns = generatedMap.representation()[0].length;

        assertEquals(expectedRows, actualRows);
        assertEquals(expectedColumns, actualColumns);
    }

    @Test
    void loadReturnsExpectedAmountsOfSymbols() throws FileNotFoundException {
        int expectedMountainNumber = 70;
        int expectedPitNumber = 17;
        int expectedMineralNumber = 10;
        int expectedWaterNumber = 10;

        MapModel generatedMap = MapLoader.load(mapFile,32,32);
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
        String expectedMapString = MapLoader.readMapFile(mapFile);
        String actualMapString = MapLoader.load(mapFile,32,32).toString().replaceAll("\n", "");

        assertEquals(expectedMapString, actualMapString);
    }

}