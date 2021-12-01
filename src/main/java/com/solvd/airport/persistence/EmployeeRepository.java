package com.solvd.airport.persistence;

import com.solvd.airport.domain.Employee;
import com.solvd.airport.domain.exception.InsertException;

public interface EmployeeRepository {

    void create(Employee employee, Long positionId, Long airlineId) throws InsertException;

}
