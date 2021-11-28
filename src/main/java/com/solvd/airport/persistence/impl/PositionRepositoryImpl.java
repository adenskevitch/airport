package com.solvd.airport.persistence.impl;

import com.solvd.airport.domain.Position;
import com.solvd.airport.domain.exception.InsertException;
import com.solvd.airport.persistence.ConnectionPool;
import com.solvd.airport.persistence.PositionRepository;

import java.sql.*;

public class PositionRepositoryImpl implements PositionRepository {

    public static final ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static final String POSITION_ENTRY_FIELD = "insert into Positions(name) values (?)";

    @Override
    public void create(Position position) throws InsertException {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(POSITION_ENTRY_FIELD, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, position.getName());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                position.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new InsertException("Position entry failed.", e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }
}
