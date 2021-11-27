package com.solvd.airport.service.impl;

import com.solvd.airport.domain.Aircraft;
import com.solvd.airport.persistence.AircraftRepository;
import com.solvd.airport.persistence.impl.AircraftRepositoryImpl;
import com.solvd.airport.service.AircraftService;
import com.solvd.airport.service.AirlineService;

public class AircraftServiceImpl implements AircraftService {

    private final AircraftRepository aircraftRepository;
    private final AirlineService airlineService;

    public AircraftServiceImpl() {
        this.aircraftRepository = new AircraftRepositoryImpl();
        this.airlineService = new AirlineServiceImpl();
    }

    @Override
    public Aircraft insert(Aircraft aircraft, Long airlineId) {
        aircraft.setId(null);
        aircraftRepository.insert(aircraft, airlineId);
        return aircraft;
    }
}
