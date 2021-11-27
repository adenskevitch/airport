package com.solvd.airport.persistence.impl;

import com.solvd.airport.domain.Flight;
import com.solvd.airport.persistence.ConnectionPool;
import com.solvd.airport.persistence.FlightRepository;

import java.sql.*;

public class FlightRepositoryImpl implements FlightRepository {

    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public void insert(Flight flight, Long aircraftId, Long directionFromId, Long directionToId, Long employeeId) {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement("insert into Flights(aircraft_id, direction_from, direction_to, employee_id, number) values (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, aircraftId);
            preparedStatement.setLong(2, directionFromId);
            preparedStatement.setLong(3, directionToId);
            preparedStatement.setLong(4, employeeId);
            preparedStatement.setInt(5, flight.getNumber());
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                flight.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to create Address", e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }
}
