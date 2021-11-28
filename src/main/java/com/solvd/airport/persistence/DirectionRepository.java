package com.solvd.airport.persistence;

import com.solvd.airport.domain.Direction;
import com.solvd.airport.domain.exception.InsertException;
import com.solvd.airport.domain.exception.UpdateDatabaseException;

public interface DirectionRepository {

    void create(Direction direction, Long airportId) throws InsertException;

    String changeDirection(String beforeLocality, String afterLocality) throws UpdateDatabaseException;

}
