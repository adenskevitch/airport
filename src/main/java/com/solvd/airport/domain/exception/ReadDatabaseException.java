package com.solvd.airport.domain.exception;

import java.sql.SQLException;

public class ReadDatabaseException extends Exception {

    public ReadDatabaseException(String msg, SQLException e) {
        super(msg, e);
    }

    public ReadDatabaseException(String msg) {
        super(msg);
    }

}
