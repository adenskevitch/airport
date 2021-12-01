package com.solvd.airport.service;

import com.solvd.airport.domain.Aircraft;
import com.solvd.airport.domain.exception.ReadDatabaseException;

import java.util.List;

public interface AircraftService {

    Aircraft create(Aircraft aircraft, Long airlineId);

    List<Aircraft> getAircraftList(String countryName);

}
