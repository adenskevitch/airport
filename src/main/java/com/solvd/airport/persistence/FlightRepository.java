package com.solvd.airport.persistence;

import com.solvd.airport.domain.Flight;
import com.solvd.airport.domain.exception.InsertException;

public interface FlightRepository {

    void insert(Flight flight, Long aircraftId, Long directionFrom, Long directionTo, Long employeeId) throws InsertException;

}
