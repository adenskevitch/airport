package com.solvd.airport.service;

import com.solvd.airport.domain.Ticket;

import java.util.List;

public interface TicketService {

    Ticket create(Ticket ticket, Long passengerId, Long flightId);

    List<Ticket> getTicket();

}
