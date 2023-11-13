package com.codecool.marsexploration.mapexplorer.configuration;

import com.codecool.marsexploration.mapexplorer.map.MapModel;

public interface ConfigurationValidator {
    boolean validate(MapModel map);

}
