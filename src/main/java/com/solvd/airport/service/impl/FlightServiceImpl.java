package com.solvd.airport.service.impl;

import com.solvd.airport.domain.*;
import com.solvd.airport.domain.exception.InsertException;
import com.solvd.airport.persistence.FlightRepository;
import com.solvd.airport.persistence.impl.mybatis.FlightMapperImpl;
import com.solvd.airport.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FlightServiceImpl implements FlightService {

    private static final Logger LOGGER = LogManager.getLogger();

    private final FlightRepository flightRepository;
    private final DirectionService directionService;
    private final AirlineService airlineService;
    private final AircraftService aircraftService;

    public FlightServiceImpl() {
        this.flightRepository = new FlightMapperImpl();
//        this.flightRepository = new FlightRepositoryImpl();
        this.directionService = new DirectionServiceImpl();
        this.airlineService = new AirlineServiceImpl();
        this.aircraftService = new AircraftServiceImpl();
    }

    @Override
    public Flight create(Flight flight, Long employeeId) {
        flight.setId(null);
        if (flight.getAircraft() != null) {
            Aircraft aircraft = aircraftService.create(flight.getAircraft(), 1L);
            flight.setAircraft(aircraft);
        }
        if (flight.getFrom() != null) {
            Direction directionFrom = directionService.create(flight.getFrom(), flight.getFrom().getAirport().getId());
            flight.setFrom(directionFrom);
        }
        if (flight.getTo() != null) {
            Direction directionTo = directionService.create(flight.getTo(), flight.getTo().getAirport().getId());
            flight.setTo(directionTo);
        }
        try {
            flightRepository.create(flight, employeeId);
        } catch (InsertException e) {
            LOGGER.debug(e.getMessage());
        }
        return flight;
    }
}
