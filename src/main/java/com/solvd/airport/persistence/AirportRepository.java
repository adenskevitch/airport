package com.solvd.airport.persistence;

import com.solvd.airport.domain.Airport;
import com.solvd.airport.domain.exception.InsertException;

public interface AirportRepository {

    void insert(Airport airport, Long addressId) throws InsertException;

}
