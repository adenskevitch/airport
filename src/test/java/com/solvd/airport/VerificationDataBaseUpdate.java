package com.solvd.airport;

import com.solvd.airport.service.AircraftService;
import com.solvd.airport.service.AirlineService;
import com.solvd.airport.service.impl.AircraftServiceImpl;
import com.solvd.airport.service.impl.AirlineServiceImpl;
import org.testng.Assert;
import org.testng.annotations.*;

@Test(groups = "update")
public class VerificationDataBaseUpdate {

    @BeforeSuite(groups = "insert")
    public void beforeSuite() {
        System.out.println("Verification data base requests suite get started");
    }

    @BeforeMethod
    public void beforeUpdateMethod() {
        System.out.println("New update test to start...");
    }

    @AfterMethod
    public void afterUpdateTest() {
        System.out.println("Update test was finish.");
    }

    public void verifyTransferAirplane() {
        AircraftService aircraftService = new AircraftServiceImpl();
        AirlineService airlineService = new AirlineServiceImpl();
        int aircraftExcepted = aircraftService.getAircraftList("Ukraine").size() + 1;
        Assert.assertEquals(airlineService.transferAircrafts("t532123", "Ukraine").size(), aircraftExcepted);
    }

    @Test(dependsOnMethods = "verifyTransferAirplane")
    public void verifyReverseAirplaneTransfer() {
        AircraftService aircraftService = new AircraftServiceImpl();
        AirlineService airlineService = new AirlineServiceImpl();
        int aircraftExcepted = aircraftService.getAircraftList("Russia").size() + 1;
        Assert.assertEquals(airlineService.transferAircrafts("t532123", "Russia").size(), aircraftExcepted);
    }
}
