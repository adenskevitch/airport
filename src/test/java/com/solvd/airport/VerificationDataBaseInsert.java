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

    private AddressService addressService;
    private AirportService airportService;
    private Address addressExample;
    private Airport airportExample;
    private Airport aptWithoutAddress;

    @Parameters({"country", "locality", "airportName"})
    @BeforeClass(groups = "insert")
    public void beforeInsertClass(String country, String locality, String airportName) {
        System.out.println("VerificationDataBaseInsert is running");
        this.addressService = new AddressServiceImpl();
        this.airportService = new AirportServiceImpl();
        this.addressExample = new Address(country, locality);
        this.airportExample = new Airport(airportName, this.addressExample);
        this.aptWithoutAddress = new Airport(airportName, null);
    }

    @AfterClass(groups = "insert")
    public void afterInsertClass() {
        System.out.println("VerificationDataBaseInsert was finished");
    }

    @Test(groups = "insert")
    public void addressInsert() {
        Assert.assertNotNull(addressService.create(addressExample), "Insert Error!");
    }

    @Test(dependsOnMethods = "addressInsert", groups = "insert")
    public void incorrectAirportInsert() {
        Assert.assertNull(airportService.create(aptWithoutAddress, null).getId(), "Airport without address was added!");
    }

    @Test(dependsOnMethods = "addressInsert", groups = "insert")
    public void correctAirportInsert() {
        Assert.assertEquals(airportService.create(airportExample, null).getAddress(), addressExample, "The address in the database does not match with entered");
    }
}
