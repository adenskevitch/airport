package com.solvd.airport.persistence;

import com.solvd.airport.domain.Ticket;
import com.solvd.airport.domain.exception.InsertException;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TicketRepository {

    void create(@Param("ticket") Ticket ticket, @Param("passengerId") Long passengerId, @Param("flightId") Long flightId) throws InsertException;

    List<Ticket> getTickets();

}
