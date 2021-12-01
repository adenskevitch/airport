package com.solvd.airport.service;

import com.solvd.airport.domain.Aircraft;
import com.solvd.airport.domain.Airline;
import com.solvd.airport.domain.exception.InsertException;
import com.solvd.airport.domain.exception.ReadDatabaseException;
import com.solvd.airport.domain.exception.UpdateDatabaseException;

import java.util.List;

public interface AirlineService {

    Airline create(Airline airline) throws InsertException;

    List<Aircraft> transferAircrafts(String bordNumber, String to) throws UpdateDatabaseException, ReadDatabaseException;

}
