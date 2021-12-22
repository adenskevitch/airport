package com.solvd.airport;

import com.solvd.airport.domain.Passenger;
import com.solvd.airport.service.AddressService;
import com.solvd.airport.service.AirlineService;
import com.solvd.airport.service.PassengerService;
import com.solvd.airport.service.impl.AddressServiceImpl;
import com.solvd.airport.service.impl.AirlineServiceImpl;
import com.solvd.airport.service.impl.PassengerServiceImpl;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class VerificationDataBaseSelect {

    @BeforeTest(groups = "select", alwaysRun = true)
    public void beforeSelectTest() {
        System.out.println("Any select operation from DB...");
    }

    @AfterTest(groups = "select")
    public void afterSelectTest() {
        System.out.println("Select was finished.");
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
            groups = {"select", "passenger"})
    public void verifySelectPerson(String name, String surname) {
        PassengerService passengerService = new PassengerServiceImpl();
        Passenger passengerExample = new Passenger(name, surname);
        passengerService.getTickets()
                .forEach(passenger -> Assert.assertNotEquals(passenger.toString(), passengerExample.toString(), "Person is found."));
    }

    @Test(groups = {"select"}, dependsOnGroups = "insert")
    public void verifySelectLastAddress() {
        AddressService addressService = new AddressServiceImpl();
        Assert.assertEquals("Netherlands", addressService.getLastAddress().getCountry(), "Row is not found.");
    }

    @Test(groups = "select")
    public void verifyGetAirlineInfo() {
        AirlineService airlineService = new AirlineServiceImpl();
        Assert.assertNotNull(airlineService.getAirlineInfo(), "Information hasn't present.");
    }
}
