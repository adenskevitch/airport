package com.solvd.airport.persistence;

import com.solvd.airport.domain.Airline;
import com.solvd.airport.domain.exception.InsertException;
import com.solvd.airport.domain.exception.UpdateDatabaseException;

public interface AirlineRepository {

    void create(Airline airline) throws InsertException;

    void transferAircrafts(String from, String to) throws UpdateDatabaseException;

}
