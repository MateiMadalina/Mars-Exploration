package com.codecool.marsexploration.mapexplorer.logger;

import java.time.LocalDateTime;

public class ConsoleLogger implements Logger{
    @Override
    public void log(String message) {
        logMessage(message, "INFO");
    }

    public void logError(String message) {
        logMessage(message, "ERROR");
    }

    private void logMessage(String message, String type) {
        String entry = "[" + LocalDateTime.now() + "] " +  type + ": " + message;
        System.out.println(entry);
    }
}
