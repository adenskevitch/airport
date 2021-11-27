package com.solvd.airport.service.impl;

import com.solvd.airport.domain.Ticket;
import com.solvd.airport.persistence.TicketRepository;
import com.solvd.airport.persistence.impl.TicketRepositoryImpl;
import com.solvd.airport.service.TicketService;

public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;

    public TicketServiceImpl() {
        this.ticketRepository = new TicketRepositoryImpl();
    }

    @Override
    public Ticket insert(Ticket ticket, Long passengerId, Long flightId) {
        ticket.setId(null);
        ticketRepository.insert(ticket, passengerId, flightId);
        return ticket;
    }
}
