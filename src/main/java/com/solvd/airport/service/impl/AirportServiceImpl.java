package com.solvd.airport.service.impl;

import com.solvd.airport.domain.Address;
import com.solvd.airport.domain.Airport;
import com.solvd.airport.domain.exception.InsertException;
import com.solvd.airport.persistence.AirportRepository;
import com.solvd.airport.persistence.impl.AirportRepositoryImpl;
import com.solvd.airport.service.AddressService;
import com.solvd.airport.service.AirportService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.stream.IntStream;

public class AirportServiceImpl implements AirportService {

    private static final Logger LOGGER = LogManager.getLogger();

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
            Address address = null;
            try {
                address = addressService.insert(airport.getAddress());
            } catch (InsertException e) {
                LOGGER.debug(e.getMessage());
            }
            airport.setAddress(address);
        }
        try {
            airportRepository.insert(airport, addressId);
        } catch (InsertException e) {
            LOGGER.debug(e.getMessage());
        }
        return airport;
    }

    @Override
    public List<Airport> insertList(List<Airport> airportList) {

        IntStream.range(0, airportList.size()).forEach(i -> {
            airportList.get(i).setId(null);
            if (airportList.get(i).getAddress() != null) {
                Address address = null;
                try {
                    address = addressService.insert((airportList.get(i).getAddress()));
                } catch (InsertException e) {
                    LOGGER.debug(e.getMessage());
                }
                airportList.get(i).setAddress(address);
                try {
                    airportRepository.insert(airportList.get(i), airportList.get(i).getAddress().getId());
                } catch (InsertException e) {
                    LOGGER.debug(e.getMessage());
                }
            }
        });

        return airportList;
    }
}

