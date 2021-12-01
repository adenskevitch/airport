package com.solvd.airport.service.impl;

import com.solvd.airport.domain.Employee;
import com.solvd.airport.domain.Position;
import com.solvd.airport.domain.exception.InsertException;
import com.solvd.airport.persistence.EmployeeRepository;
import com.solvd.airport.persistence.impl.EmployeeRepositoryImpl;
import com.solvd.airport.service.EmployeeService;
import com.solvd.airport.service.PositionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOGGER = LogManager.getLogger();

    private final EmployeeRepository employeeRepository;
    private final PositionService positionService;

    public EmployeeServiceImpl() {
        this.employeeRepository = new EmployeeRepositoryImpl();
        this.positionService = new PositionServiceImpl();
    }

    @Override
    public Employee create(Employee employee, Long positionId, Long airlineId) {
        employee.setId(null);
        if(employee.getPosition()!=null){
            Position position=positionService.create(employee.getPosition());
            employee.setPosition(position);
        }
        try {
            employeeRepository.create(employee, positionId, airlineId);
        } catch (InsertException e) {
            LOGGER.debug(e.getMessage());
        }
        return employee;
    }
}
