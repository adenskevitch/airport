package com.solvd.airport.service.impl;

import com.solvd.airport.domain.Address;
import com.solvd.airport.domain.exception.InsertException;
import com.solvd.airport.persistence.AddressRepository;
import com.solvd.airport.persistence.impl.mybatis.AddressMapperImpl;
import com.solvd.airport.service.AddressService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class AddressServiceImpl implements AddressService {

    private static final Logger LOGGER = LogManager.getLogger();

    private final AddressRepository addressRepository;

    public AddressServiceImpl() {
        this.addressRepository = new AddressMapperImpl();
//        this.addressRepository = new AddressRepositoryImpl();
    }

    @Override
    public Address create(Address address) {
        address.setId(null);
        try {
            addressRepository.create(address);
        } catch (InsertException e) {
            LOGGER.debug(e.getMessage());
        }
        return address;
    }

    @Override
    public List<Address> createList(List<Address> addressList) {
        addressList.forEach(address -> {
            address.setId(null);
            try {
                addressRepository.create(address);
            } catch (InsertException e) {
                LOGGER.debug(e.getMessage());
            }
        });
        return addressList;
    }

    @Override
    public List<Address> getAddressesList() {
        return addressRepository.getAddressesList();
    }
}
