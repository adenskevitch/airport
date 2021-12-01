package com.solvd.airport;

import com.solvd.airport.domain.*;
import com.solvd.airport.domain.exception.DeleteException;
import com.solvd.airport.domain.exception.ReadDatabaseException;
import com.solvd.airport.domain.exception.UpdateDatabaseException;
import com.solvd.airport.service.*;
import com.solvd.airport.service.impl.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) {
        /* Create objects*/
        List<Address> addresses = new ArrayList<>();
        addresses.add(new Address("Netherlands", "Amsterdam"));
        addresses.add(new Address("Russia", "Moscow"));
        addresses.add(new Address("Ukraine", "Kiev"));
        addresses.add(new Address("Poland", "Warshaw"));
        addresses.add(new Address("France", "Paris"));
        addresses.add(new Address("United Kingdom", "London"));
        addresses.add(new Address("Germany", "Berlin"));
        addresses.add(new Address("Italy", "Rome"));
        addresses.add(new Address("Spain", "Madrid"));
        addresses.add(new Address("Turkey", "Istanbul"));

        List<Airport> airportList = new ArrayList<>();
        airportList.add(new Airport("Amsterdam Airport Schiphol", addresses.get(0)));
        airportList.add(new Airport("Sheremetyevo International Airport", addresses.get(1)));
        airportList.add(new Airport("Boryspil International Airport", addresses.get(2)));
        airportList.add(new Airport("Warsaw Chopin Airport", addresses.get(3)));
        airportList.add(new Airport("Charles de Gaulle Airport", addresses.get(4)));
        airportList.add(new Airport("Heathrow Airport", addresses.get(5)));
        airportList.add(new Airport("Berlin Tegel Airport", addresses.get(6)));
        airportList.add(new Airport("Leonardo da Vinci–Fiumicino Airport", addresses.get(7)));
        airportList.add(new Airport("Adolfo Suárez Madrid–Barajas Airport", addresses.get(8)));
        airportList.add(new Airport("Istanbul Airport", addresses.get(9)));

        Address address1 = new Address();
        address1.setCountry("Netherlands");
        address1.setLocality("Amsterdam");

        Aircraft aircraft1 = new Aircraft();
        aircraft1.setBoardNumber("a523654667");
        aircraft1.setType("airbus A330");
        aircraft1.setSeatsCount(750);
        Aircraft aircraft2 = new Aircraft();
        aircraft2.setBoardNumber("a6342352");
        aircraft2.setType("airbus A330");
        aircraft2.setSeatsCount(750);
        List<Aircraft> aircraftList = new ArrayList<>();
        aircraftList.add(aircraft1);
        aircraftList.add(aircraft2);

        Position position1 = new Position();
        position1.setName("first pilot");
        Position position2 = new Position();
        position2.setName("second pilot");

        Employee employee1 = new Employee();
        employee1.setName("Anton");
        employee1.setSurname("Jons");
        employee1.setPosition(position1);
        Employee employee2 = new Employee();
        employee2.setName("Joana");
        employee2.setSurname("Lither");
        employee2.setPosition(position2);
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee1);
        employeeList.add(employee2);

        Airline airline1 = new Airline();
        airline1.setAircrafts(aircraftList);
        airline1.setEmployees(employeeList);
        airline1.setName("Amsterdam Airlines");
        airline1.setCountry("Netherlands");

        Airport airport1 = new Airport();
        airport1.setAddress(address1);
        airport1.setName("Amsterdam Airport Schiphol");

        Direction directionFrom = new Direction();
        directionFrom.setAirport(airport1);
        directionFrom.setTime(LocalDateTime.now());
        Direction directionTo = new Direction();
        directionTo.setAirport(airport1);
        directionTo.setTime(LocalDateTime.now());

        Flight flight1 = new Flight();
        flight1.setFrom(directionFrom);
        flight1.setTo(directionTo);
        flight1.setAircraft(aircraft1);
        flight1.setNumber(6501);

        Ticket ticket1 = new Ticket();
        ticket1.setCost(45.50);
        ticket1.setDateOfSale(LocalDateTime.now());
        ticket1.setFlight(flight1);

        Passenger passenger1 = new Passenger();
        passenger1.setName("Edward");
        passenger1.setSurname("Gregory");
        passenger1.setPassportNumber("GT234567");
        List<Ticket> ticketList = new ArrayList<>();
        ticketList.add(ticket1);
        passenger1.setTickets(ticketList);

        /*Query to DB*/
        AirlineService airlineService = new AirlineServiceImpl();
        try {
            LOGGER.debug(airlineService.transferAircrafts("t532123", "Russia"));
        } catch (UpdateDatabaseException | ReadDatabaseException e) {
            LOGGER.debug(e.getMessage());
        }

        PassengerService passengerService = new PassengerServiceImpl();
        try {
            LOGGER.debug(passengerService.deleteFromPassengersList("Scott", "Simpson"));
        } catch (DeleteException | ReadDatabaseException e) {
            LOGGER.debug(e.getMessage());
        }

        /*Insert to DB*/
//        AddressService addressService = new AddressServiceImpl();
//        addressService.insertList(addresses);
//
//        AirportService airportService = new AirportServiceImpl();
//        airportService.insertList(airportList);
//
//        AirlineService airlineService = new AirlineServiceImpl();
//        airlineService.insert(airline1);
//
//        AircraftService aircraftService = new AircraftServiceImpl();
//        aircraftService.insert(aircraft1, 1L);
//
//        DirectionService directionService = new DirectionServiceImpl();
//        directionService.insert(directionFrom, 1L);
//        directionService.insert(directionTo, 1L);
//
//        PositionService positionService = new PositionServiceImpl();
//        positionService.insert(position1);
//
//        EmployeeService employeeService = new EmployeeServiceImpl();
//        employeeService.insert(employee1, 1L, 1L);
//
//        FlightService flightService = new FlightServiceImpl();
//        flightService.insert(flight1, 1L, 1L, 2L, 1L);
//
//        PassengerService passengerService = new PassengerServiceImpl();
//        passengerService.insert(passenger1);
//
//        TicketService ticketService = new TicketServiceImpl();
//        ticketService.insert(ticket1, 1L, 1L);
    }
}
