package com.solvd.airport.persistence.impl;

import com.solvd.airport.domain.Aircraft;
import com.solvd.airport.persistence.AircraftRepository;
import com.solvd.airport.persistence.ConnectionPool;

import java.sql.*;

public class AircraftRepositoryImpl implements AircraftRepository {

    public static final ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public void insert(Aircraft aircraft, Long airlineId) {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement("insert into Aircrafts(airline_id, board_number, type, seats_count) values (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, airlineId);
            preparedStatement.setString(2, aircraft.getBoardNumber());
            preparedStatement.setString(3, aircraft.getType());
            preparedStatement.setInt(4, aircraft.getSeatsCount());

            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                aircraft.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to create Address", e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }
}
