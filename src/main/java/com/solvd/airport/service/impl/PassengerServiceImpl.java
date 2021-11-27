package com.solvd.airport.service.impl;

import com.solvd.airport.domain.Passenger;
import com.solvd.airport.persistence.PassengerRepository;
import com.solvd.airport.persistence.impl.PassengerRepositoryImpl;
import com.solvd.airport.service.PassengerService;

public class PassengerServiceImpl implements PassengerService {

    private final PassengerRepository passengerRepository;

    public PassengerServiceImpl() {
        this.passengerRepository = new PassengerRepositoryImpl();
    }

    @Override
    public Passenger insert(Passenger passenger) {
        passenger.setId(null);
        passengerRepository.insert(passenger);
        return passenger;
    }
}
