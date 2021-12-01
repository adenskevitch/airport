package com.solvd.airport.persistence.impl;

import com.solvd.airport.domain.Ticket;
import com.solvd.airport.domain.exception.InsertException;
import com.solvd.airport.persistence.ConnectionPool;
import com.solvd.airport.persistence.TicketRepository;

import java.sql.*;

public class TicketRepositoryImpl implements TicketRepository {

    private static final ConnectionPool connectionPoll = ConnectionPool.getInstance();

    private static final String TICKET_ENTRY_FIELD = "Insert into Tickets(passenger_id, flight_id, date_of_sell, cost) values (?, ?, ?, ?)";

    @Override
    public void create(Ticket ticket, Long passengerId, Long flightId) throws InsertException {
        Connection connection = connectionPoll.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(TICKET_ENTRY_FIELD, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, passengerId);
            preparedStatement.setLong(2, flightId);
            preparedStatement.setTimestamp(3, Timestamp.valueOf(ticket.getDateOfSale()));
            preparedStatement.setDouble(4, ticket.getCost());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                ticket.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new InsertException("Ticket entry failed.", e);
        } finally {
            connectionPoll.releaseConnection(connection);
        }
    }
}
