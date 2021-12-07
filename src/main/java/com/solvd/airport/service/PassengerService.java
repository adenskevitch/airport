package com.solvd.airport.service;

import com.solvd.airport.domain.Passenger;
import com.solvd.airport.domain.exception.DeleteException;
import com.solvd.airport.domain.exception.InsertException;
import com.solvd.airport.domain.exception.ReadDatabaseException;

import java.util.List;

public interface PassengerService {

    Passenger create(Passenger passenger);

    List<Passenger> createList(List<Passenger> passengerList);

    List<Passenger> deleteFromPassengersList(String name, String surname);

    List<Passenger> getTickets();

}
