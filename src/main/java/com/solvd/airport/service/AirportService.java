package com.solvd.airport.service;

import com.solvd.airport.domain.Airport;

import java.util.List;

public interface AirportService {

    Airport create(Airport airport, Long addressId);

    List<Airport> createList(List<Airport> airportList);
}
