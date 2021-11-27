package com.solvd.airport.persistence;

import com.solvd.airport.domain.Employee;

public interface EmployeeRepository {

    void insert(Employee employee, Long positionId, Long airlineId);

}
