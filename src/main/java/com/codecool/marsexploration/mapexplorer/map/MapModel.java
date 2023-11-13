package com.codecool.marsexploration.mapexplorer.map;

public record MapModel(String[][] representation) {
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
    public String[][] getRepresentation() {
        return representation;
    }

    @Override
    public String toString() {
        return createStringRepresentation(representation);
    }
}
