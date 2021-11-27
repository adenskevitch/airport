package com.solvd.airport.service.impl;

import com.solvd.airport.domain.Address;
import com.solvd.airport.persistence.AddressRepository;
import com.solvd.airport.persistence.impl.AddressRepositoryImpl;
import com.solvd.airport.service.AddressService;

import java.util.List;

public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    public AddressServiceImpl() {
        this.addressRepository = new AddressRepositoryImpl();
    }

    @Override
    public Address insert(Address address) {
            address.setId(null);
            addressRepository.insert(address);
        return address;
    }

    @Override
    public List<Address> insertList(List<Address> addressList) {
        addressList.forEach(address -> {
            address.setId(null);
            addressRepository.insert(address);
        });
        return addressList;
    }
}
