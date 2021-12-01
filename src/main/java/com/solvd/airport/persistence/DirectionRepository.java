package com.solvd.airport.persistence;

import com.solvd.airport.domain.Direction;
import com.solvd.airport.domain.exception.InsertException;
import com.solvd.airport.domain.exception.UpdateDatabaseException;
import org.apache.ibatis.annotations.Param;

public interface DirectionRepository {

    void create(@Param("direction") Direction direction, @Param("airportId") Long airportId) throws InsertException;

    String changeDirection(@Param("beforeLocality") String beforeLocality, @Param("afterLocality") String afterLocality) throws UpdateDatabaseException;

}
