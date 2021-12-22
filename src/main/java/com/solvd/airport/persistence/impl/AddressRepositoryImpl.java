package com.solvd.airport.persistence.impl;

import com.solvd.airport.domain.Address;
import com.solvd.airport.domain.exception.DeleteException;
import com.solvd.airport.domain.exception.InsertException;
import com.solvd.airport.persistence.AddressRepository;
import com.solvd.airport.persistence.ConnectionPool;

import java.sql.*;
import java.util.List;

public class AddressRepositoryImpl implements AddressRepository {

    public static final ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static final String ADDRESS_FIELD = "insert into Addresses(country, locality) values (?, ?)";

    @Override
    public void create(Address address) throws InsertException {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(ADDRESS_FIELD, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, address.getCountry());
            preparedStatement.setString(2, address.getLocality());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                address.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new InsertException("Address entry failed", e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public List<Address> getAddressesList() {
        return null;
    }

    @Override
    public Address getLastAddress() {
        return null;
    }

    @Override
    public void deleteFromAddresses(String country, String locality) throws DeleteException {

    }
}
