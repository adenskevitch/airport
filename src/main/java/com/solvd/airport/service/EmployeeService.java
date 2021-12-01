package com.solvd.airport.service;

import com.solvd.airport.domain.Employee;

public interface EmployeeService {

    Employee create(Employee employee, Long positionId, Long airlineId);

}
