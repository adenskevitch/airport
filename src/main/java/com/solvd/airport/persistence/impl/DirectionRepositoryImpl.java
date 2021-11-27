package com.solvd.airport.persistence.impl;

import com.solvd.airport.domain.Direction;
import com.solvd.airport.persistence.ConnectionPool;
import com.solvd.airport.persistence.DirectionRepository;

import java.sql.*;

public class DirectionRepositoryImpl implements DirectionRepository {

    public static final ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public void insert(Direction direction, Long airportId) {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement("insert into Directions(airport_id, time) values (?,?)", Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, airportId);
            preparedStatement.setTimestamp(2, Timestamp.valueOf(direction.getTime()));
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                direction.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to create Address", e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }
}
