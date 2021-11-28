package com.solvd.airport.persistence;

import com.solvd.airport.domain.Aircraft;
import com.solvd.airport.domain.exception.InsertException;
import com.solvd.airport.domain.exception.ReadDatabaseException;

import java.util.List;

public interface AircraftRepository {

    void insert(Aircraft aircraft, Long airlineId) throws InsertException;

    List<Aircraft> selectAircraftList(String countryName) throws ReadDatabaseException;

}
