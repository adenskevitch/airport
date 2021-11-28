package com.solvd.airport.persistence.impl;

import com.solvd.airport.domain.Aircraft;
import com.solvd.airport.domain.exception.InsertException;
import com.solvd.airport.domain.exception.ReadDatabaseException;
import com.solvd.airport.persistence.AircraftRepository;
import com.solvd.airport.persistence.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AircraftRepositoryImpl implements AircraftRepository {

    public static final ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static final String SELECT_AIRCRAFTS_LIST = "Select ac.id as AircraftID, ac.airline_id as AirlineID, ac.type as Model, ac.board_number as BoardNumber " +
                                                             "from Aircrafts ac left join Airlines al on ac.airline_id=al.id where al.country=?";

    @Override
    public void insert(Aircraft aircraft, Long airlineId) throws InsertException {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement("insert into Aircrafts(airline_id, board_number, type, seats_count) values (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, airlineId);
            preparedStatement.setString(2, aircraft.getBoardNumber());
            preparedStatement.setString(3, aircraft.getType());
            preparedStatement.setInt(4, aircraft.getSeatsCount());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                aircraft.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new InsertException("Aircraft entry failed", e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public List<Aircraft> selectAircraftList(String countryName) throws ReadDatabaseException {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_AIRCRAFTS_LIST)) {
            preparedStatement.setString(1,countryName);
            ResultSet resultSet = preparedStatement.executeQuery();
            return mapAircraft(resultSet);
        } catch (SQLException e) {
            throw new ReadDatabaseException("Database read failed. Check your request.", e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    private List<Aircraft> mapAircraft(ResultSet resultSet) throws SQLException {
        List<Aircraft> aircraftList = new ArrayList<>();
        while (resultSet.next()) {
            Aircraft aircraft = new Aircraft();
            aircraft.setId(resultSet.getLong("AircraftID"));
            aircraft.setType(resultSet.getString("Model"));
            aircraft.setBoardNumber(resultSet.getString("BoardNumber"));
            aircraftList.add(aircraft);
        }
        return aircraftList;
    }
}
