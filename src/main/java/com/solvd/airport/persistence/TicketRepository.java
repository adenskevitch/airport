package com.solvd.airport.persistence;

import com.solvd.airport.domain.Ticket;
import com.solvd.airport.domain.exception.InsertException;

public interface TicketRepository {

    void create(Ticket ticket, Long passengerId, Long flightId) throws InsertException;

}
