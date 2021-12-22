package com.solvd.airport;

import com.solvd.airport.domain.Passenger;
import com.solvd.airport.service.AddressService;
import com.solvd.airport.service.AirlineService;
import com.solvd.airport.service.PassengerService;
import com.solvd.airport.service.impl.AddressServiceImpl;
import com.solvd.airport.service.impl.AirlineServiceImpl;
import com.solvd.airport.service.impl.PassengerServiceImpl;
import org.testng.Assert;
import org.testng.annotations.*;

public class VerificationDataBaseSelect {

    private AddressService addressService;
    private AirlineService airlineService;

    @BeforeGroups(groups = {"select"})
    public void beforeDeleteGroupTest() {
        System.out.println("Delete operations are running...");
        this.addressService = new AddressServiceImpl();
        this.airlineService = new AirlineServiceImpl();
    }

    @AfterGroups(groups = "select")
    public void afterDeleteGroupTest() {
        System.out.println("Delete operations was completed...");
        this.addressService = null;
        this.airlineService = null;
    }

    @DataProvider(name = "passengerList")
    public Object[][] passengersList() {
        return new Object[][]{
                {"Edward", "Gregory"}, {"Scott", "Simpson"},
                {"Aron", "Scot"}, {"Sandra", "Gardner"},
                {"Emma", "Richards"}, {"Anne", "Gordon"},
                {"Philomena", "Haynes"}, {"Joshua", "Cox"}
        };
    }

    @Test(dataProvider = "passengerList",
            alwaysRun = true,
            groups = {"select", "passenger"})
    public void verifySelectPerson(String name, String surname) {
        PassengerService passengerService = new PassengerServiceImpl();
        Passenger passengerExample = new Passenger(name, surname);
        passengerService.getTickets()
                .forEach(passenger -> Assert.assertNotEquals(passenger.toString(), passengerExample.toString(), "Person is found."));
    }

    @Test(groups = {"select"}, dependsOnGroups = "insert")
    public void verifySelectLastAddress() {
        Assert.assertEquals("Netherlands", addressService.getLastAddress().getCountry(), "Row is not found.");
    }

    @Test(groups = "select")
    public void verifyGetAirlineInfo() {
        Assert.assertNotNull(airlineService.getAirlineInfo(), "Information hasn't present.");
    }
}
