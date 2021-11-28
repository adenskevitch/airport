package com.solvd.airport.service;

import com.solvd.airport.domain.Aircraft;
import com.solvd.airport.domain.exception.ReadDatabaseException;

import java.util.List;

public interface AircraftService {

    Aircraft insert(Aircraft aircraft, Long airlineId);

    List<Aircraft> selectAircraftList(String countryName) throws ReadDatabaseException;

}
