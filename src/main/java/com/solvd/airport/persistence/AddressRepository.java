package com.solvd.airport.persistence;

import com.solvd.airport.domain.Address;
import com.solvd.airport.domain.exception.InsertException;

import java.util.List;

public interface AddressRepository {

    void create(Address address) throws InsertException;

    List<Address> getAddressesList();
}
