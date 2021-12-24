package com.solvd.airport.persistence;

import com.solvd.airport.domain.Address;
import com.solvd.airport.domain.exception.DeleteException;
import com.solvd.airport.domain.exception.InsertException;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AddressRepository {

    void create(Address address) throws InsertException;

    List<Address> getAddressesList();

    Address getLastAddress();

    void deleteFromAddresses(@Param("country") String country, @Param("locality") String locality) throws DeleteException;

}
