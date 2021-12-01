package com.solvd.airport.domain.exception;

import java.sql.SQLException;

public class InsertException extends UpdateDatabaseException {

    public InsertException(String msg, SQLException e) {
        super(msg, e);
    }

    public InsertException(String msg) {
        super(msg);
    }
}
