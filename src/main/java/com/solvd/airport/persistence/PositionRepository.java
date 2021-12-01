package com.solvd.airport.persistence;

import com.solvd.airport.domain.Position;
import com.solvd.airport.domain.exception.InsertException;

public interface PositionRepository {

    void create(Position position) throws InsertException;

}
