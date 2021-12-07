package com.solvd.airport.persistence;

import com.solvd.airport.domain.Passenger;
import com.solvd.airport.domain.exception.DeleteException;
import com.solvd.airport.domain.exception.InsertException;
import com.solvd.airport.domain.exception.ReadDatabaseException;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PassengerRepository {

    void create(Passenger passenger) throws InsertException;

    void deleteFromPassengersList(@Param("name") String name, @Param("surname") String surname) throws DeleteException;

    List<Passenger> getPassengerList() throws ReadDatabaseException;

    List<Passenger> getTickets() throws ReadDatabaseException;

}
