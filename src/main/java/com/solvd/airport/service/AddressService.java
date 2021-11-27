package com.solvd.airport.service;

import com.solvd.airport.domain.Address;

import java.util.List;

public interface AddressService {

    Address insert(Address address);

    List<Address> insertList(List<Address> addressList);

}
