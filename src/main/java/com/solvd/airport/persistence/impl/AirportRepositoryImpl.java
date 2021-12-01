package com.solvd.airport.persistence.impl;

import com.solvd.airport.domain.Airport;
import com.solvd.airport.domain.exception.InsertException;
import com.solvd.airport.persistence.AirportRepository;
import com.solvd.airport.persistence.ConnectionPool;

import java.sql.*;

public class AirportRepositoryImpl implements AirportRepository {

    public static final ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static final String AIRPORT_ENTRY_FIELD = "Insert into Airports(address_id, name) values (?,?)";

    @Override
    public void create(Airport airport, Long addressId) throws InsertException {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(AIRPORT_ENTRY_FIELD, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, addressId);
            preparedStatement.setString(2, airport.getName());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                airport.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new InsertException("Airport entry failed", e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }
}
