package com.solvd.airport.service.impl;

import com.solvd.airport.domain.Passenger;
import com.solvd.airport.domain.exception.DeleteException;
import com.solvd.airport.domain.exception.InsertException;
import com.solvd.airport.domain.exception.ReadDatabaseException;
import com.solvd.airport.persistence.PassengerRepository;
import com.solvd.airport.persistence.impl.PassengerRepositoryImpl;
import com.solvd.airport.service.PassengerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class PassengerServiceImpl implements PassengerService {

    private static final Logger LOGGER = LogManager.getLogger();

    private final PassengerRepository passengerRepository;

    public PassengerServiceImpl() {
        this.passengerRepository = new PassengerRepositoryImpl();
    }

    @Override
    public Passenger insert(Passenger passenger) {
        passenger.setId(null);
        try {
            passengerRepository.insert(passenger);
        } catch (InsertException e) {
            LOGGER.debug(e.getMessage());
        }
        return passenger;
    }

    @Override
    public List<Passenger> insertList(List<Passenger> passengerList) {
        passengerList.forEach(passenger -> {
            passenger.setId(null);
            try {
                passengerRepository.insert(passenger);
            } catch (InsertException e) {
                LOGGER.debug(e.getMessage());
            }
        });
        return passengerList;
    }

    @Override
    public List<Passenger> deleteFromPassengersList(String name, String surname) throws DeleteException, ReadDatabaseException {
        passengerRepository.deleteFromPassengersList(name, surname);
        return passengerRepository.selectPassengerList();
    }
}
