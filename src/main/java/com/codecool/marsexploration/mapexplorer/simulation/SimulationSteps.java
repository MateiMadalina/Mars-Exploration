package com.codecool.marsexploration.mapexplorer.simulation;

import com.codecool.marsexploration.mapexplorer.routines.BuildingRoutine;
import com.codecool.marsexploration.mapexplorer.routines.ExplorationRoutine;
import com.codecool.marsexploration.mapexplorer.routines.ExtractionRoutine;
import com.codecool.marsexploration.mapexplorer.routines.Routine;

import java.util.List;

public class SimulationSteps {
    private final List<Routine> routines;
    public SimulationSteps(List<Routine> routines) {
        this.routines = routines;
    }

    public void runRoutines(){
        BuildingRoutine buildingRoutine = null;
        if(routines.get(1) instanceof BuildingRoutine currentRoutine){
            buildingRoutine = currentRoutine;
        }
        for(Routine routine : routines) {
            if(routine instanceof ExtractionRoutine currentRoutine){
                assert buildingRoutine != null;
                currentRoutine.setCommandCenter(buildingRoutine.getCommandCenter());
            }
            routine.run();
            if(routine instanceof ExplorationRoutine currentRoutine) {
                if(!currentRoutine.isColonizable()) {
                    break;
                }
            }
        }
    }
}
