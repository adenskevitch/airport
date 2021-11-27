package com.solvd.airport.persistence.impl;

import com.solvd.airport.domain.Airport;
import com.solvd.airport.persistence.AirportRepository;
import com.solvd.airport.persistence.ConnectionPool;

import java.sql.*;

public class AirportRepositoryImpl implements AirportRepository {

    public static final ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public void insert(Airport airport, Long addressId) {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement("insert into Airports(address_id, name) values (?,?)", Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, addressId);
            preparedStatement.setString(2, airport.getName());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                airport.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to create Address", e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }
}
