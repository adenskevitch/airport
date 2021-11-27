package com.solvd.airport.service.impl;

import com.solvd.airport.domain.Employee;
import com.solvd.airport.domain.Position;
import com.solvd.airport.persistence.EmployeeRepository;
import com.solvd.airport.persistence.impl.EmployeeRepositoryImpl;
import com.solvd.airport.service.EmployeeService;
import com.solvd.airport.service.PositionService;

public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final PositionService positionService;

    public EmployeeServiceImpl() {
        this.employeeRepository = new EmployeeRepositoryImpl();
        this.positionService = new PositionServiceImpl();
    }

    @Override
    public Employee insert(Employee employee, Long positionId, Long airlineId) {
        employee.setId(null);
        if(employee.getPosition()!=null){
            Position position=positionService.insert(employee.getPosition());
            employee.setPosition(position);
        }
        employeeRepository.insert(employee, positionId, airlineId);
        return employee;
    }
}
