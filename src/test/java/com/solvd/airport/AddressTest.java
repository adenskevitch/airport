package com.solvd.airport;

import com.solvd.airport.domain.Address;
import com.solvd.airport.domain.Airport;
import com.solvd.airport.service.AddressService;
import com.solvd.airport.service.AirportService;
import com.solvd.airport.service.impl.AddressServiceImpl;
import com.solvd.airport.service.impl.AirportServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;

public class AddressTest {

    private static final Logger LOGGER = LogManager.getLogger();

    private final AddressService addressService;
    private final AirportService airportService;
    private final Address addressExample;
    private final Airport airportExample;
    private final Airport aptWithoutAddress;


    @Parameters({"country", "locality", "airportName"})
    public AddressTest(String country, String locality, String airportName) {
        this.addressService = new AddressServiceImpl();
        this.airportService = new AirportServiceImpl();
        this.addressExample = new Address(country, locality);
        this.airportExample = new Airport(airportName, this.addressExample);
        this.aptWithoutAddress = new Airport(airportName, null);
    }

    @BeforeClass(groups = "insert")
    public void beforeInsertTests() {
        LOGGER.info("VerificationDataBaseInsert is running");
    }

    @AfterClass(groups = "insert")
    public void afterInsertTests() {
        LOGGER.info("VerificationDataBaseInsert was finished");
    }

    @Test(groups = "insert")
    public void verifyAddressInsertTest() {
        Address address = addressService.create(addressExample);
        Assert.assertNotNull(address, "Insert Error!");
    }

    @Test(dependsOnMethods = "verifyAddressInsertTest", groups = {"insert"})
    public void negativeAirportInsertTest() {
        Airport airport = airportService.create(aptWithoutAddress, null);
        Assert.assertNull(airport.getId(), "Airport without address was added!");
    }

    @Test(dependsOnMethods = "verifyAddressInsertTest", groups = "insert")
    public void positiveAirportInsertTest() {
        Airport airport = airportService.create(airportExample, null);
        Assert.assertEquals(airport.getAddress(), addressExample, "The address in the database does not match with entered");
    }

    @Test(groups = {"delete", "address"})
    public void verifyAddressDeleteTest() {
        addressService.deleteFromAddresses(addressExample.getCountry(), addressExample.getLocality());
        Assert.assertEquals(addressExample.getCountry(), addressService.getLastAddress().getCountry(), "Deleting was failed");
    }

    @Test(groups = {"select"})
    public void verifySelectLastAddressTest() {
        Address address = addressService.getLastAddress();
        Assert.assertEquals("Netherlands", address.getCountry(), "Row is not found.");
    }
}
