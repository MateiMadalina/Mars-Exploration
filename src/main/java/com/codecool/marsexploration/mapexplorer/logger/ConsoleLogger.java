package com.codecool.marsexploration.mapexplorer.logger;

public class ConsoleLogger implements Logger{
    @Override
    public void log(String message) {
        logMessage(message, "INFO");
    }

    public void logError(String message) {
        logMessage(message, "ERROR");
    }

    private void logMessage(String message, String type) {
        String entry = type + ": " + message;
        System.out.println(entry);
    }
}
