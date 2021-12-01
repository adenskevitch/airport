package com.solvd.airport.service.impl;

import com.solvd.airport.domain.Aircraft;
import com.solvd.airport.domain.Airline;
import com.solvd.airport.domain.Employee;
import com.solvd.airport.domain.exception.InsertException;
import com.solvd.airport.domain.exception.ReadDatabaseException;
import com.solvd.airport.domain.exception.UpdateDatabaseException;
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
    public Airline create(Airline airline) throws InsertException {
        airline.setId(null);
        airlineRepository.create(airline);
        if (airline.getEmployees() != null) {
            List<Employee> employeeList = airline.getEmployees().stream()
                    .map(employee -> employeeService.create(employee, positionService.create(employee.getPosition()).getId(), airline.getId()))
                    .collect(Collectors.toList());
            airline.setEmployees(employeeList);
        }
        if (airline.getAircrafts() != null) {
            List<Aircraft> aircraftList = airline.getAircrafts().stream()
                    .map(aircraft -> aircraftService.create(aircraft, airline.getId()))
                    .collect(Collectors.toList());
            airline.setAircrafts(aircraftList);
        }
        return airline;
    }

    @Override
    public List<Aircraft> transferAircrafts(String bordNumber, String toCountry) throws UpdateDatabaseException, ReadDatabaseException {
        airlineRepository.transferAircrafts(bordNumber, toCountry);
        return aircraftService.getAircraftList(toCountry);
    }
}
