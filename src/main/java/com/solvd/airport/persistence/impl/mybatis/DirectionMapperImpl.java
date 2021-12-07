package com.solvd.airport.persistence.impl.mybatis;

import com.solvd.airport.domain.Direction;
import com.solvd.airport.domain.exception.InsertException;
import com.solvd.airport.domain.exception.UpdateDatabaseException;
import com.solvd.airport.persistence.DirectionRepository;
import com.solvd.airport.persistence.MyBatisSessionHolder;
import org.apache.ibatis.session.SqlSession;

public class DirectionMapperImpl implements DirectionRepository {
    @Override
    public void create(Direction direction, Long airportId) throws InsertException {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession()) {
            session.getMapper(DirectionRepository.class).create(direction, airportId);
        }
    }

    @Override
    public String changeDirection(String beforeLocality, String afterLocality) throws UpdateDatabaseException {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            return session.getMapper(DirectionRepository.class).changeDirection(beforeLocality, afterLocality);
        }
    }
}
