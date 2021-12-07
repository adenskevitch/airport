package com.solvd.airport.persistence;

import com.solvd.airport.domain.Airport;
import com.solvd.airport.domain.exception.InsertException;
import org.apache.ibatis.annotations.Param;

public interface AirportRepository {

    void create(@Param("airport") Airport airport, @Param("addressId") Long addressId) throws InsertException;

}
