package com.solvd.airport;

import com.solvd.airport.service.AircraftService;
import com.solvd.airport.service.AirlineService;
import com.solvd.airport.service.impl.AircraftServiceImpl;
import com.solvd.airport.service.impl.AirlineServiceImpl;
import org.testng.Assert;
import org.testng.annotations.*;

@Test(groups = "update")
public class VerificationDataBaseUpdate {

    AircraftService aircraftService;
    AirlineService airlineService;

    @BeforeSuite
    public void beforeSuite() {
        //startAnyDriver
        System.out.println("Verification data base requests suite get started...");
    }

    @AfterSuite
    public void afterSuite() {
        //closeDriver, unloadingResources
        System.out.println("Verification data base requests suite was finish");
    }

    @BeforeTest
    public void beforeSelectTest() {
        //initialisation required data for test
        System.out.println("Any update operation...");
        this.aircraftService = new AircraftServiceImpl();
        this.airlineService = new AirlineServiceImpl();
    }

    @AfterTest
    public void afterSelectTest() {
        System.out.println("Update was finished.");
        this.airlineService = null;
        this.aircraftService = null;
    }

    public void verifyTransferAirplane() {
        int aircraftExcepted = aircraftService.getAircraftList("Ukraine").size() + 1;
        Assert.assertEquals(airlineService.transferAircrafts("t532123", "Ukraine").size(), aircraftExcepted);
    }

    @Test(dependsOnMethods = "verifyTransferAirplane")
    public void verifyReverseAirplaneTransfer() {
        int aircraftExcepted = aircraftService.getAircraftList("Russia").size() + 1;
        Assert.assertEquals(airlineService.transferAircrafts("t532123", "Russia").size(), aircraftExcepted);
    }
}
