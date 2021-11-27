package com.solvd.airport.persistence;

import com.solvd.airport.domain.Position;

public interface PositionRepository {

    void insert(Position position);

}
