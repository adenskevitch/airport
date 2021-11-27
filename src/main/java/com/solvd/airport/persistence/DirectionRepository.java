package com.solvd.airport.persistence;

import com.solvd.airport.domain.Direction;

public interface DirectionRepository {

    void insert(Direction direction, Long airportId);

}
