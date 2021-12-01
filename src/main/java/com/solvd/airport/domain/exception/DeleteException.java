package com.solvd.airport.domain.exception;

import java.sql.SQLException;

public class DeleteException extends UpdateDatabaseException {
    public DeleteException(String msg, SQLException e) {
        super(msg, e);
    }

    public DeleteException(String msg) {
        super(msg);
    }
}
