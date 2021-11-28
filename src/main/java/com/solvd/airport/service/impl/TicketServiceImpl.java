package com.solvd.airport.service.impl;

import com.solvd.airport.domain.Ticket;
import com.solvd.airport.domain.exception.InsertException;
import com.solvd.airport.persistence.TicketRepository;
import com.solvd.airport.persistence.impl.TicketRepositoryImpl;
import com.solvd.airport.service.TicketService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TicketServiceImpl implements TicketService {

    private static final Logger LOGGER = LogManager.getLogger();

    private final TicketRepository ticketRepository;

    public TicketServiceImpl() {
        this.ticketRepository = new TicketRepositoryImpl();
    }

    @Override
    public Ticket insert(Ticket ticket, Long passengerId, Long flightId) {
        ticket.setId(null);
        try {
            ticketRepository.insert(ticket, passengerId, flightId);
        } catch (InsertException e) {
            LOGGER.debug(e.getMessage());
        }
        return ticket;
    }
}
