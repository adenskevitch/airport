package com.solvd.airport.persistence;

import com.solvd.airport.domain.Airport;
import com.solvd.airport.domain.exception.InsertException;

public interface AirportRepository {

    void create(Airport airport, Long addressId) throws InsertException;

}
