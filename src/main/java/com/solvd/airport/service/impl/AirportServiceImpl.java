package com.solvd.airport.service.impl;

import com.solvd.airport.domain.Address;
import com.solvd.airport.domain.Airport;
import com.solvd.airport.persistence.AirportRepository;
import com.solvd.airport.persistence.impl.AirportRepositoryImpl;
import com.solvd.airport.service.AddressService;
import com.solvd.airport.service.AirportService;

public class AirportServiceImpl implements AirportService {

    private final AirportRepository airportRepository;
    private final AddressService addressService;

    public AirportServiceImpl() {
        this.airportRepository = new AirportRepositoryImpl();
        this.addressService = new AddressServiceImpl();
    }

    @Override
    public Airport insert(Airport airport, Long addressId) {
        airport.setId(null);
        if (airport.getAddress() != null) {
            Address address = addressService.insert(airport.getAddress());
            airport.setAddress(address);
        }
        airportRepository.insert(airport, addressId);
        return airport;
    }
}
