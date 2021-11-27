package com.solvd.airport.persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class ConnectionPool {

    private static final Logger LOGGER = LogManager.getLogger();
    private static volatile ConnectionPool instance;
    private static volatile List<Connection> connectionPool;

    private ConnectionPool() {
    }

    public static synchronized ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        connectionPool = Collections.synchronizedList(new ArrayList<>());
        IntStream.range(0, Integer.parseInt(ConfigProperties.getProperty("poolSize"))).forEach(i ->
        {
            try {
                connectionPool.add(DriverManager.getConnection(ConfigProperties.getProperty("url"), ConfigProperties.getProperty("username"), ConfigProperties.getProperty("password")));
            } catch (SQLException e) {
                LOGGER.debug(e.getMessage());
            }
        });
        return instance;
    }

    public synchronized void releaseConnection(Connection connection) {
        connectionPool.add(connection);
    }

    public synchronized Connection getConnection() {
        return connectionPool
                .remove(connectionPool.size() - 1);
    }
}
