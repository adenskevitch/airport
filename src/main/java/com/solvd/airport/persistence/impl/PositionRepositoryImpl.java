package com.solvd.airport.persistence.impl;

import com.solvd.airport.domain.Position;
import com.solvd.airport.persistence.ConnectionPool;
import com.solvd.airport.persistence.PositionRepository;

import java.sql.*;

public class PositionRepositoryImpl implements PositionRepository {

    public static final ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public void insert(Position position) {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement("insert into Positions(name) values (?)", Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, position.getName());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                position.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to create Address", e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }
}
