package com.solvd.airport.service.impl;

import com.solvd.airport.domain.Address;
import com.solvd.airport.domain.Airport;
import com.solvd.airport.domain.exception.InsertException;
import com.solvd.airport.persistence.AirportRepository;
import com.solvd.airport.persistence.mappersimpl.AirportMapperImpl;
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
        this.airportRepository = new AirportMapperImpl();
//        this.airportRepository = new AirportRepositoryImpl();
        this.addressService = new AddressServiceImpl();
    }

    @Override
    public Airport create(Airport airport, Long addressId) {
        airport.setId(null);
        if (airport.getAddress() != null) {
            Address address = addressService.create(airport.getAddress());
            airport.setAddress(address);
            try {
                airportRepository.create(airport, address.getId());
            } catch (InsertException e) {
                LOGGER.debug(e.getMessage());
            }
        }
        return airport;
    }

    @Override
    public List<Airport> createList(List<Airport> airportList) {

        IntStream.range(0, airportList.size()).forEach(i -> {
            airportList.get(i).setId(null);
            if (airportList.get(i).getAddress() != null) {
                Address address;
                address = addressService.create((airportList.get(i).getAddress()));
                airportList.get(i).setAddress(address);
                try {
                    airportRepository.create(airportList.get(i), airportList.get(i).getAddress().getId());
                } catch (InsertException e) {
                    LOGGER.debug(e.getMessage());
                }
            }
        });

        return airportList;
    }
}

