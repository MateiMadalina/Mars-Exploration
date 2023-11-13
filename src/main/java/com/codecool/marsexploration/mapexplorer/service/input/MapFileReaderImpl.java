package com.codecool.marsexploration.mapexplorer.service.input;

import com.codecool.marsexploration.mapexplorer.service.input.MapFileReader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MapFileReaderImpl implements MapFileReader {
    @Override
    public String readMapFile(String filePath) throws FileNotFoundException {
        StringBuilder characters = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                characters.append(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Could not load map file. Try placing it in the App's folder or pass the filename as args.");
            throw new FileNotFoundException("Map file not found.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return characters.toString();
    }
}
