package com.solvd.airport.service;

import com.solvd.airport.domain.Address;
import com.solvd.airport.domain.exception.InsertException;

import java.util.List;

public interface AddressService {

    Address create(Address address);

    List<Address> createList(List<Address> addressList);

    List<Address> getAddressesList();

}
