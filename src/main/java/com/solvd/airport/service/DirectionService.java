package com.solvd.airport.service;

import com.solvd.airport.domain.Direction;

public interface DirectionService {

    Direction insert(Direction direction, Long airportId);

}
