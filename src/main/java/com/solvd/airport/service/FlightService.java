package com.solvd.airport.service;

import com.solvd.airport.domain.Flight;

public interface FlightService {

    Flight insert(Flight flight, Long aircraftId, Long directionFrom, Long directionTo, Long employeeId);

}
