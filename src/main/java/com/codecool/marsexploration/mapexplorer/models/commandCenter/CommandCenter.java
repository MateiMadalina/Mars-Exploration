package com.codecool.marsexploration.mapexplorer.models.commandCenter;

import com.codecool.marsexploration.mapexplorer.map.Coordinate;

import java.util.*;

public class CommandCenter {
    private final int id;
    private final Coordinate location;
    private Status status;
    private final Map<String, List<Coordinate>> resourcesOnStock;
    private static int count = 1;

    public CommandCenter(Coordinate location, Status status, Map<String, List<Coordinate>> resourcesOnStock) {
        this.id = count;
        this.location = location;
        this.status = status;
        this.resourcesOnStock = resourcesOnStock;
        count++;
    }

    public void addResourcesToCommandCenter(String symbol,Coordinate resourceLocation){
        if(getResourcesOnStock().containsKey(symbol)){
            resourcesOnStock.get(symbol).add(resourceLocation);
        }else{
            resourcesOnStock.put(symbol, new ArrayList<>(Collections.singletonList(resourceLocation)));
        }
    }

    public Coordinate getLocation() {
        return location;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
    public Map<String, List<Coordinate>> getResourcesOnStock() {
        return resourcesOnStock;
    }

    @Override
    public String toString() {
        return "CommandCenter{" +
                "id=" + id +
                ", location=" + location +
                ", status=" + status +
                ", resourcesOnStock=" + resourcesOnStock +
                '}';
    }
}
