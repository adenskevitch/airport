package com.solvd.airport.service;

import com.solvd.airport.domain.Passenger;
import com.solvd.airport.domain.exception.DeleteException;
import com.solvd.airport.domain.exception.InsertException;
import com.solvd.airport.domain.exception.ReadDatabaseException;

import java.util.List;

public interface PassengerService {

    Passenger insert(Passenger passenger) throws InsertException;

    List<Passenger> insertList(List<Passenger> passengerList);

    List<Passenger> deleteFromPassengersList(String name, String surname) throws DeleteException, ReadDatabaseException;

}
