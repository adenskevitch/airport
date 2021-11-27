package com.solvd.airport.persistence.impl;

import com.solvd.airport.domain.Passenger;
import com.solvd.airport.persistence.ConnectionPool;
import com.solvd.airport.persistence.PassengerRepository;

import java.sql.*;

public class PassengerRepositoryImpl implements PassengerRepository {

    public static final ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public void insert(Passenger passenger) {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement("insert into Passengers(name, surname, passport_number) values (?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, passenger.getName());
            preparedStatement.setString(2, passenger.getSurname());
            preparedStatement.setString(3, passenger.getPassportNumber());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                passenger.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to create Address", e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }
}
