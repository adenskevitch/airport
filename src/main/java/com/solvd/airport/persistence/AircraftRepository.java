package com.solvd.airport.persistence;

import com.solvd.airport.domain.Aircraft;
import com.solvd.airport.domain.exception.InsertException;
import com.solvd.airport.domain.exception.ReadDatabaseException;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AircraftRepository {

    void create(@Param("aircraft") Aircraft aircraft, @Param("airlineId") Long airlineId) throws InsertException;

    List<Aircraft> getAircraftList(String countryName) throws ReadDatabaseException;
}
