package com.solvd.airport.persistence.impl;

import com.solvd.airport.domain.Airline;
import com.solvd.airport.persistence.AirlineRepository;
import com.solvd.airport.persistence.ConnectionPool;

import java.sql.*;

public class AirlineRepositoryImpl implements AirlineRepository {

    public static final ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public void insert(Airline airline) {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement("insert into Airlines(name, country) values (?,?)", Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, airline.getName());
            preparedStatement.setString(2, airline.getCountry());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                airline.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to create Address", e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }
}
