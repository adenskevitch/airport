package com.solvd.airport.service.impl;

import com.solvd.airport.domain.Aircraft;
import com.solvd.airport.domain.exception.InsertException;
import com.solvd.airport.domain.exception.ReadDatabaseException;
import com.solvd.airport.persistence.AircraftRepository;
import com.solvd.airport.persistence.impl.AircraftRepositoryImpl;
import com.solvd.airport.service.AircraftService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class AircraftServiceImpl implements AircraftService {

    private static final Logger LOGGER = LogManager.getLogger();

    private final AircraftRepository aircraftRepository;

    public AircraftServiceImpl() {
        this.aircraftRepository = new AircraftRepositoryImpl();
    }

    @Override
    public Aircraft create(Aircraft aircraft, Long airlineId) {
        aircraft.setId(null);
        try {
            aircraftRepository.create(aircraft, airlineId);
        } catch (InsertException e) {
            LOGGER.debug(e.getMessage());
        }
        return aircraft;
    }

    @Override
    public List<Aircraft> getAircraftList(String countryName) throws ReadDatabaseException {
        return aircraftRepository.getAircraftList(countryName);
    }
}
