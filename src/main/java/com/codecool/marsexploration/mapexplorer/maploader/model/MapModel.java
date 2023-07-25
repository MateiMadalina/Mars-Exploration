package com.codecool.marsexploration.mapexplorer.maploader.model;

public class MapModel {
    private String[][] representation;
    private boolean successfullyGenerated;

    public MapModel(String[][] representation, boolean successfullyGenerated) {
        this.representation = representation;
        this.successfullyGenerated = successfullyGenerated;
    }

    public int getDimension() {
        return representation.length;
    }

    private static String createStringRepresentation(String[][] arr) {
        StringBuilder sb = new StringBuilder();

        for (String[] strings : arr) {
            StringBuilder s = new StringBuilder();
            for (String string : strings) {
                s.append(string == null ? " " : string);
            }

            sb.append(s).append("\n");
        }

        return sb.toString();
    }

    public String getByCoordinate(Coordinate coordinate) {
        return representation[coordinate.x()][coordinate.y()];
    }

    public boolean isEmpty(Coordinate coordinate) {
        return representation[coordinate.x()][coordinate.y()] == null
                || representation[coordinate.y()][coordinate.y()].isEmpty()
                || representation[coordinate.y()][coordinate.y()].equals(" ");
    }

    @Override
    public String toString() {
        return createStringRepresentation(representation);
    }

    public String[][] getRepresentation() {
        return representation;
    }
}
