package com.solvd.airport.persistence.impl.mybatis;

import com.solvd.airport.domain.Passenger;
import com.solvd.airport.domain.exception.DeleteException;
import com.solvd.airport.domain.exception.InsertException;
import com.solvd.airport.domain.exception.ReadDatabaseException;
import com.solvd.airport.persistence.MyBatisSessionHolder;
import com.solvd.airport.persistence.PassengerRepository;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class PassengerMapperImpl implements PassengerRepository {
    @Override
    public void create(Passenger passenger) throws InsertException {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            session.getMapper(PassengerRepository.class).create(passenger);
        }
    }

    @Override
    public void deleteFromPassengersList(String name, String surname) throws DeleteException {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            session.getMapper(PassengerRepository.class).deleteFromPassengersList(name, surname);
        }
    }

    @Override
    public List<Passenger> getPassengerList() throws ReadDatabaseException {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            return session.getMapper(PassengerRepository.class).getPassengerList();
        }
    }

    @Override
    public List<Passenger> getTickets() throws ReadDatabaseException {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            return session.getMapper(PassengerRepository.class).getTickets();
        }
    }
}
