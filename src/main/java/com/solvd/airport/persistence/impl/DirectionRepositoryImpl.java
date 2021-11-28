package com.solvd.airport.persistence.impl;

import com.solvd.airport.domain.Direction;
import com.solvd.airport.domain.exception.InsertException;
import com.solvd.airport.domain.exception.UpdateDatabaseException;
import com.solvd.airport.persistence.ConnectionPool;
import com.solvd.airport.persistence.DirectionRepository;

import java.sql.*;

public class DirectionRepositoryImpl implements DirectionRepository {

    public static final ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static final String CHANGE_DIRECTION = "update Directions d join Airports ai " + "on d.airport_id=ai.id " + "join Addresses ad " + " on ai.address_id=ad.id" +
            " set d.airport_id=(select id " + "from Airports " + "  where Airports.address_id=(select id " + "from Addresses " + "  where Addresses.locality=?))" +
            " where d.airport_id=(select id " + "from Airports " + " where Airports.address_id=(select id " + "from Addresses " + " where Addresses.locality=?));";
    private static final String DIRECTION_ENTRY_FIELD = "insert into Directions(airport_id, time) values (?,?)";

    @Override
    public void insert(Direction direction, Long airportId) throws InsertException{
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DIRECTION_ENTRY_FIELD, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, airportId);
            preparedStatement.setTimestamp(2, Timestamp.valueOf(direction.getTime()));
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                direction.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new InsertException("Direction entry failed.", e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public String changeDirection(String beforeLocality, String afterLocality) throws UpdateDatabaseException {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(CHANGE_DIRECTION)) {
            preparedStatement.setString(1, afterLocality);
            preparedStatement.setString(2, beforeLocality);
            Integer lineNumber = preparedStatement.executeUpdate();
            if (lineNumber.equals(0)) {
                throw new UpdateDatabaseException("No changes made.");
            }
        } catch (SQLException e) {
            throw new UpdateDatabaseException("Modified failed");
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return "Direction was modified";
    }
}
