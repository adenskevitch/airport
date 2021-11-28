package com.solvd.airport.service;

import com.solvd.airport.domain.Ticket;

public interface TicketService {

    Ticket create(Ticket ticket, Long passengerId, Long flightId);

}
