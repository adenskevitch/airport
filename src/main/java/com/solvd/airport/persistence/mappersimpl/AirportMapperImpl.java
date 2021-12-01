package com.solvd.airport.persistence.mappersimpl;

import com.solvd.airport.domain.Airport;
import com.solvd.airport.domain.exception.InsertException;
import com.solvd.airport.persistence.AirportRepository;
import com.solvd.airport.persistence.MyBatisSessionHolder;
import org.apache.ibatis.session.SqlSession;

public class AirportMapperImpl implements AirportRepository {

    @Override
    public void create(Airport airport, Long addressId) throws InsertException {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            session.getMapper(AirportRepository.class).create(airport, addressId);
        }
    }
}
