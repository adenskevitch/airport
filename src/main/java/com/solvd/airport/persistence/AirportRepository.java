package com.solvd.airport.persistence;

import com.solvd.airport.domain.Airport;

public interface AirportRepository {

    void insert(Airport airport, Long addressId);

}
