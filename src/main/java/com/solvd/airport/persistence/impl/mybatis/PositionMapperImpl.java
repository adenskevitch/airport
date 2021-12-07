package com.solvd.airport.persistence.impl.mybatis;

import com.solvd.airport.domain.Position;
import com.solvd.airport.domain.exception.InsertException;
import com.solvd.airport.persistence.MyBatisSessionHolder;
import com.solvd.airport.persistence.PositionRepository;
import org.apache.ibatis.session.SqlSession;

public class PositionMapperImpl implements PositionRepository {

    @Override
    public void create(Position position) throws InsertException {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            session.getMapper(PositionRepository.class).create(position);
        }
    }
}
