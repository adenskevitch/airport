package com.solvd.airport.persistence;

import com.solvd.airport.domain.Flight;
import com.solvd.airport.domain.exception.InsertException;
import org.apache.ibatis.annotations.Param;

public interface FlightRepository {

    void create(@Param("flight") Flight flight, @Param("employeeId") Long employeeId) throws InsertException;

}
