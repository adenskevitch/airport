package com.solvd.airport.service;

import com.solvd.airport.domain.Airport;

public interface AirportService {

    Airport insert(Airport airport, Long addressId);

}
