package com.solvd.airport.service;

import com.solvd.airport.domain.Address;

import java.util.List;

public interface AddressService {

    Address create(Address address);

    List<Address> createList(List<Address> addressList);

    List<Address> getAddressesList();

    Address getLastAddress();

    void deleteFromAddresses(String country, String locality);
}
