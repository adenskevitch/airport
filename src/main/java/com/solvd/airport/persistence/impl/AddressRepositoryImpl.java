package com.solvd.airport.persistence.impl;

import com.solvd.airport.domain.Address;
import com.solvd.airport.persistence.AddressRepository;
import com.solvd.airport.persistence.ConnectionPool;

import java.sql.*;

public class AddressRepositoryImpl implements AddressRepository {

    public static final ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public void insert(Address address) {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement("insert into Addresses(country, locality) values (?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, address.getCountry());
            preparedStatement.setString(2, address.getLocality());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                address.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to create Address", e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }
}
