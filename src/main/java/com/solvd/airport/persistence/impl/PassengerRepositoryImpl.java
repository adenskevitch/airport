package com.solvd.airport.persistence.impl;

import com.solvd.airport.domain.Passenger;
import com.solvd.airport.domain.exception.DeleteException;
import com.solvd.airport.domain.exception.InsertException;
import com.solvd.airport.domain.exception.ReadDatabaseException;
import com.solvd.airport.persistence.ConnectionPool;
import com.solvd.airport.persistence.PassengerRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PassengerRepositoryImpl implements PassengerRepository {

    public static final ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static final String DELETE_PASSENGER = "delete from Passengers where name=? and surname=?;";
    private static final String SELECT_LIST = "Select name as Name, surname as Surname from Passengers";
    private static final String PASSENGER_ENTRY_FIELD = "insert into Passengers(name, surname, passport_number) values (?,?,?)";

    @Override
    public void create(Passenger passenger) throws InsertException {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(PASSENGER_ENTRY_FIELD, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, passenger.getName());
            preparedStatement.setString(2, passenger.getSurname());
            preparedStatement.setString(3, passenger.getPassportNumber());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                passenger.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new InsertException("Passenger entry failed", e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void deleteFromPassengersList(String name, String surname) throws DeleteException {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PASSENGER)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, surname);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DeleteException("Delete error.", e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public List<Passenger> getPassengerList() throws ReadDatabaseException {
        Connection connection = connectionPool.getConnection();
        List<Passenger> passengerList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_LIST)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Passenger passenger = new Passenger();
                passenger.setName(resultSet.getString("Name"));
                passenger.setSurname(resultSet.getString("Surname"));
                passengerList.add(passenger);
            }
        } catch (SQLException e) {
            throw new ReadDatabaseException("Read failed.", e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return passengerList;
    }

    @Override
    public List<Passenger> getTickets() {
        return null;
    }
}
