package com.solvd.airport.persistence;

import com.solvd.airport.domain.Aircraft;

public interface AircraftRepository {

    void insert(Aircraft aircraft, Long airlineId);

}
