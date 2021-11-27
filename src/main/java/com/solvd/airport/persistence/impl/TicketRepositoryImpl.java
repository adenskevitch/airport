package com.solvd.airport.persistence.impl;

import com.solvd.airport.domain.Ticket;
import com.solvd.airport.persistence.ConnectionPool;
import com.solvd.airport.persistence.TicketRepository;

import java.sql.*;

public class TicketRepositoryImpl implements TicketRepository {

    private static final ConnectionPool connectionPoll = ConnectionPool.getInstance();

    @Override
    public void insert(Ticket ticket, Long passengerId, Long flightId) {
        Connection connection = connectionPoll.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement("Insert into Tickets(passenger_id, flight_id, date_of_sell, cost) values (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, passengerId);
            preparedStatement.setLong(2, flightId);
            preparedStatement.setTimestamp(3, Timestamp.valueOf(ticket.getDateOfSale()));
            preparedStatement.setDouble(4, ticket.getCost());
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                ticket.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to insert Tickets");
        } finally {
            connectionPoll.releaseConnection(connection);
        }
    }
}
