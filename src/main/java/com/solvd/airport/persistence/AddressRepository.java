package com.solvd.airport.persistence;

import com.solvd.airport.domain.Address;
import com.solvd.airport.domain.exception.InsertException;

public interface AddressRepository {

    void insert(Address address) throws InsertException;

}
