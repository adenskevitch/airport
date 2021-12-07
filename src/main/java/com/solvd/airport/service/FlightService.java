package com.solvd.airport.service;

import com.solvd.airport.domain.Flight;

public interface FlightService {

    Flight create(Flight flight, Long employeeId);

}
