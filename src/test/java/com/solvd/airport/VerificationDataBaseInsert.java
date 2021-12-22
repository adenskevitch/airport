package com.solvd.airport;

import com.solvd.airport.domain.Address;
import com.solvd.airport.domain.Airport;
import com.solvd.airport.service.AddressService;
import com.solvd.airport.service.AirportService;
import com.solvd.airport.service.impl.AddressServiceImpl;
import com.solvd.airport.service.impl.AirportServiceImpl;
import org.testng.Assert;
import org.testng.annotations.*;

public class VerificationDataBaseInsert {

    @BeforeClass(groups = "insert")
    public void beforeInsertClass() {
        System.out.println("VerificationDataBaseInsert is running");
    }

    @AfterClass(groups = "insert")
    public void afterInsertClass() {
        System.out.println("VerificationDataBaseInsert was finished");
    }

    @Parameters({"country", "locality"})
    @Test(groups = "insert")
    public void addressInsert(String country, String locality) {
        AddressService addressService = new AddressServiceImpl();
        Address addressExample = new Address(country, locality);
        Assert.assertNotNull(addressService.create(addressExample), "Insert Error!");
    }

    @Parameters({"airportName"})
    @Test(dependsOnMethods = "addressInsert", groups = "insert")
    public void incorrectAirportInsert(String airportName) {
        AirportService airportService = new AirportServiceImpl();
        Airport airportExample = new Airport(airportName, null);
        Assert.assertNull(airportService.create(airportExample, null).getId(), "Airport without address was added!");
    }

    @Parameters({"country", "locality", "airportName"})
    @Test(dependsOnMethods = "addressInsert", groups = "insert")
    public void correctAirportInsert(String country, String locality, String airportName) {
        AirportService airportService = new AirportServiceImpl();
        Address addressExample = new Address(country, locality);
        Airport airportExample = new Airport(airportName, addressExample);
        Assert.assertEquals(airportService.create(airportExample, null).getAddress(), addressExample, "The address in the database does not match with entered");
    }
}
