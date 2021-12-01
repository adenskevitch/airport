package com.solvd.airport.persistence;

import com.solvd.airport.domain.Passenger;
import com.solvd.airport.domain.exception.DeleteException;
import com.solvd.airport.domain.exception.InsertException;
import com.solvd.airport.domain.exception.ReadDatabaseException;

import java.util.List;

public interface PassengerRepository {

    void create(Passenger passenger) throws InsertException;

    void deleteFromPassengersList(String name, String surname) throws DeleteException;

    List<Passenger> getPassengerList() throws ReadDatabaseException;

}
