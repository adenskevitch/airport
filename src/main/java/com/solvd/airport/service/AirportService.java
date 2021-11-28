package com.solvd.airport.service;

import com.solvd.airport.domain.Airport;

import java.util.List;

public interface AirportService {

    Airport insert(Airport airport, Long addressId);

    List<Airport> insertList(List<Airport> airportList);
}
