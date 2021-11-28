package com.solvd.airport.service.impl;

import com.solvd.airport.domain.Direction;
import com.solvd.airport.domain.Flight;
import com.solvd.airport.domain.exception.InsertException;
import com.solvd.airport.persistence.FlightRepository;
import com.solvd.airport.persistence.impl.FlightRepositoryImpl;
import com.solvd.airport.service.DirectionService;
import com.solvd.airport.service.FlightService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FlightServiceImpl implements FlightService {

    private static final Logger LOGGER = LogManager.getLogger();

    private final FlightRepository flightRepository;
    private final DirectionService directionService;

    public FlightServiceImpl() {
        this.flightRepository = new FlightRepositoryImpl();
        this.directionService = new DirectionServiceImpl();
    }

    @Override
    public Flight insert(Flight flight, Long aircraftId, Long directionFromId, Long directionToId, Long employeeId) {
        flight.setId(null);
        if (flight.getFrom() != null) {
            Direction directionFrom = directionService.insert(flight.getFrom(), flight.getFrom().getAirport().getId());
            flight.setFrom(directionFrom);
        }
        if (flight.getTo() != null) {
            Direction directionTo = directionService.insert(flight.getTo(), flight.getTo().getAirport().getId());
            flight.setTo(directionTo);
        }
        try {
            flightRepository.insert(flight, aircraftId, directionFromId, directionToId, employeeId);
        } catch (InsertException e) {
            LOGGER.debug(e.getMessage());
        }
        return flight;
    }
}
