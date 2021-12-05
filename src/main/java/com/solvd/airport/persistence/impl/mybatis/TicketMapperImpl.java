package com.solvd.airport.persistence.impl.mybatis;

import com.solvd.airport.domain.Passenger;
import com.solvd.airport.domain.Ticket;
import com.solvd.airport.domain.exception.InsertException;
import com.solvd.airport.domain.exception.ReadDatabaseException;
import com.solvd.airport.persistence.MyBatisSessionHolder;
import com.solvd.airport.persistence.PassengerRepository;
import com.solvd.airport.persistence.TicketRepository;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class TicketMapperImpl implements TicketRepository {

    @Override
    public void create(Ticket ticket, Long passengerId, Long flightId) throws InsertException {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            session.getMapper(TicketRepository.class).create(ticket, passengerId, flightId);
        }
    }

    @Override
    public List<Ticket> getTickets() {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            return session.getMapper(TicketRepository.class).getTickets();
        }
    }
}