package com.solvd.airport.persistence;

import com.solvd.airport.domain.Ticket;

public interface TicketRepository {

    void insert(Ticket ticket, Long passengerId, Long flightId);

}
