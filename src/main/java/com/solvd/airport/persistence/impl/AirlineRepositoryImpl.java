package com.solvd.airport.persistence.impl;

import com.solvd.airport.domain.Airline;
import com.solvd.airport.domain.exception.InsertException;
import com.solvd.airport.domain.exception.UpdateDatabaseException;
import com.solvd.airport.persistence.AirlineRepository;
import com.solvd.airport.persistence.ConnectionPool;

import java.sql.*;

public class AirlineRepositoryImpl implements AirlineRepository {

    public static final ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static final String TRANSFER_AIRCRAFTS = "update Aircrafts ac join Airlines al " +
            "on ac.airline_id=al.id " +
            "set ac.airline_id=(select id from Airlines where Airlines.country=?)" +
            "    where ac.board_number=?;";
    private static final String AIRLINE_ENTRY_FIELD = "insert into Airlines(name, country) values (?,?)";

    @Override
    public void insert(Airline airline) throws InsertException {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(AIRLINE_ENTRY_FIELD, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, airline.getName());
            preparedStatement.setString(2, airline.getCountry());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                airline.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new InsertException("Airline entry failed", e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void transferAircrafts(String bordNumber, String to) throws UpdateDatabaseException {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(TRANSFER_AIRCRAFTS)) {
            preparedStatement.setString(1, "Ukraine");
            preparedStatement.setString(2, bordNumber);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new UpdateDatabaseException("Transfer failed.", e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }
}
