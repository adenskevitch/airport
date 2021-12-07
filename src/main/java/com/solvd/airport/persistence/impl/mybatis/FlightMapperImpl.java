package com.solvd.airport.persistence.impl.mybatis;

import com.solvd.airport.domain.Flight;
import com.solvd.airport.domain.exception.InsertException;
import com.solvd.airport.persistence.FlightRepository;
import com.solvd.airport.persistence.MyBatisSessionHolder;
import org.apache.ibatis.session.SqlSession;

public class FlightMapperImpl implements FlightRepository {

    @Override
    public void create(Flight flight, Long employeeId) throws InsertException {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            session.getMapper(FlightRepository.class).create(flight, employeeId);
        }
    }
}
