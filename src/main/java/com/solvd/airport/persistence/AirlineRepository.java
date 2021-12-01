package com.solvd.airport.persistence;

import com.solvd.airport.domain.Airline;
import com.solvd.airport.domain.exception.InsertException;
import com.solvd.airport.domain.exception.UpdateDatabaseException;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

public interface AirlineRepository {

    void create(Airline airline) throws InsertException;

    void transferAircrafts(@Param("boardNumber") String boardNumber, @Param("to") String to);

}
