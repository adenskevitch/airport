package com.solvd.airport.persistence.impl.mybatis;

import com.solvd.airport.domain.Airline;
import com.solvd.airport.domain.exception.InsertException;
import com.solvd.airport.persistence.AirlineRepository;
import com.solvd.airport.persistence.MyBatisSessionHolder;
import org.apache.ibatis.session.SqlSession;

public class AirlineMapperImpl implements AirlineRepository {

    @Override
    public void create(Airline airline) throws InsertException {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            session.getMapper(AirlineRepository.class).create(airline);
        }
    }

    @Override
    public void transferAircrafts(String boardNumber, String to) {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            session.getMapper(AirlineRepository.class).transferAircrafts(boardNumber, to);
        }
    }
}
