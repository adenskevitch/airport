package com.solvd.airport.persistence.mappersimpl;

import com.solvd.airport.domain.Aircraft;
import com.solvd.airport.domain.exception.InsertException;
import com.solvd.airport.domain.exception.ReadDatabaseException;
import com.solvd.airport.persistence.AircraftRepository;
import com.solvd.airport.persistence.MyBatisSessionHolder;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class AircraftMapperImpl implements AircraftRepository {

    @Override
    public void create(Aircraft aircraft, Long airlineId) throws InsertException {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            session.getMapper(AircraftRepository.class).create(aircraft, airlineId);
        }
    }

    @Override
    public List<Aircraft> getAircraftList(String countryName) throws ReadDatabaseException {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            return session.getMapper(AircraftRepository.class).getAircraftList(countryName);
        }
    }
}
