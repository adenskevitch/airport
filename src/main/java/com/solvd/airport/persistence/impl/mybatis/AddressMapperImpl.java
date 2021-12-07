package com.solvd.airport.persistence.impl.mybatis;

import com.solvd.airport.domain.Address;
import com.solvd.airport.domain.exception.InsertException;
import com.solvd.airport.persistence.AddressRepository;
import com.solvd.airport.persistence.MyBatisSessionHolder;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class AddressMapperImpl implements AddressRepository {

    @Override
    public void create(Address address) throws InsertException {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            session.getMapper(AddressRepository.class).create(address);
        }
    }

    @Override
    public List<Address> getAddressesList() {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            return session.getMapper(AddressRepository.class).getAddressesList();
        }
    }
}
