package com.solvd.airport.service;

import com.solvd.airport.domain.Aircraft;

public interface AircraftService {

    Aircraft insert(Aircraft aircraft, Long airlineId);

}
