package com.solvd.airport.service;

import com.solvd.airport.domain.Direction;

public interface DirectionService {

    Direction create(Direction direction, Long airportId);

}
