package com.solvd.airport.persistence.mappersimpl;

import com.solvd.airport.domain.Address;
import com.solvd.airport.domain.exception.InsertException;
import com.solvd.airport.persistence.AddressRepository;
import com.solvd.airport.persistence.MyBatisSessionHolder;
import org.apache.ibatis.session.SqlSession;

public class AddressMapperImpl implements AddressRepository {

    @Override
    public void create(Address address) throws InsertException {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            session.getMapper(AddressRepository.class).create(address);
        }
    }
}
