package com.solvd.airport.persistence;

import com.solvd.airport.domain.Airline;
import com.solvd.airport.domain.exception.InsertException;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AirlineRepository {

    void create(Airline airline) throws InsertException;

    void transferAircrafts(@Param("boardNumber") String boardNumber, @Param("to") String to);

    List<Airline> getAirlineInfo();
}
