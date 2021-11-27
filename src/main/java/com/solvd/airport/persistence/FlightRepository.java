package com.solvd.airport.persistence;

import com.solvd.airport.domain.Flight;

public interface FlightRepository {

    void insert(Flight flight, Long aircraftId, Long directionFrom, Long directionTo, Long employeeId);

}
