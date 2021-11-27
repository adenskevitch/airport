package com.solvd.airport.persistence.impl;

import com.solvd.airport.domain.Employee;
import com.solvd.airport.persistence.ConnectionPool;
import com.solvd.airport.persistence.EmployeeRepository;

import java.sql.*;

public class EmployeeRepositoryImpl implements EmployeeRepository {

    public static final ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public void insert(Employee employee, Long positionId, Long airlineId) {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement("insert into Employees(position_id, airline_id, name, surname) values (?,?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, positionId);
            preparedStatement.setLong(2, airlineId);
            preparedStatement.setString(3, employee.getName());
            preparedStatement.setString(4, employee.getSurname());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                employee.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to create Address", e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }
}
