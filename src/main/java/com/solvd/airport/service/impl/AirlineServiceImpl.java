package com.solvd.airport.service.impl;

import com.solvd.airport.domain.Aircraft;
import com.solvd.airport.domain.Airline;
import com.solvd.airport.domain.Employee;
import com.solvd.airport.persistence.AirlineRepository;
import com.solvd.airport.persistence.impl.AirlineRepositoryImpl;
import com.solvd.airport.service.AircraftService;
import com.solvd.airport.service.AirlineService;
import com.solvd.airport.service.EmployeeService;
import com.solvd.airport.service.PositionService;

import java.util.List;
import java.util.stream.Collectors;

public class AirlineServiceImpl implements AirlineService {

    private final AirlineRepository airlineRepository;
    private final EmployeeService employeeService;
    private final PositionService positionService;
    private final AircraftService aircraftService;

    public AirlineServiceImpl() {
        this.airlineRepository = new AirlineRepositoryImpl();
        this.employeeService = new EmployeeServiceImpl();
        this.positionService = new PositionServiceImpl();
        this.aircraftService = new AircraftServiceImpl();
    }

    @Override
    public Airline insert(Airline airline) {
        airline.setId(null);
        airlineRepository.insert(airline);
        if (airline.getEmployees() != null) {
            List<Employee> employeeList = airline.getEmployees().stream()
                    .map(employee -> employeeService.insert(employee, positionService.insert(employee.getPosition()).getId(), airline.getId()))
                    .collect(Collectors.toList());
            airline.setEmployees(employeeList);
        }
        if (airline.getAircrafts()!=null) {
            List<Aircraft> aircraftList = airline.getAircrafts().stream()
                    .map(aircraft -> aircraftService.insert(aircraft, airline.getId()))
                    .collect(Collectors.toList());
            airline.setAircrafts(aircraftList);
        }
        return airline;
    }
}
