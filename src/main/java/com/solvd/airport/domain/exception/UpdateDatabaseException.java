package com.solvd.airport.domain.exception;

import java.sql.SQLException;

public class UpdateDatabaseException extends SQLException {

    public UpdateDatabaseException(String msg, SQLException e) {
        super(msg);
    }
    public UpdateDatabaseException(String msg) {
        super(msg);
    }
}
