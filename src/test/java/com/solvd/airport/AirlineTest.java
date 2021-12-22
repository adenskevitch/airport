package com.solvd.airport;

import com.solvd.airport.domain.Airline;
import com.solvd.airport.service.AirlineService;
import com.solvd.airport.service.impl.AirlineServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class AirlineTest {

    private static final Logger LOGGER = LogManager.getLogger();

    @BeforeSuite
    public void beforeSuite() {
        //startAnyDriver
        LOGGER.info("Verification data base requests suite get started...");
    }

    @AfterSuite
    public void afterSuite() {
        //closeDriver, unloadingResources
        LOGGER.info("Verification data base requests suite was finish");
    }

    @BeforeTest
    public void beforeSelectTest() {
        //initialisation required data for test
        LOGGER.info("Any update operation...");
    }

    @AfterTest
    public void afterSelectTest() {
        LOGGER.info("Update was finished.");
    }

    @DataProvider(name = "airlinesList")
    public Object[][] airlineList() {
        return new Object[][]{
                {"Amsterdam Airlines", "Netherlands"}, {"Aeroflot", "Russia"},
                {"Ukraine International Airlines", "'Ukraine'"}
        };
    }

    @Test(groups = {"airline", "insert"}, dataProvider = "airlinesList", alwaysRun = true)
    public void verifyInsertAirlineTest(String airlineName, String airlineCountry) {
        Airline airlineForInsert = new Airline(airlineName, airlineCountry);
        AirlineService airlineService = new AirlineServiceImpl();
        Airline airline = airlineService.create(airlineForInsert);
        Assert.assertNotNull(airline, "Airline wasn't added");
    }

    @Test(groups = {"airline", "select"})
    public void verifyGetAirlineInfoTest() {
        AirlineService airlineService = new AirlineServiceImpl();
        List<Airline> airlinesInformationList = airlineService.getAirlineInfo();
        Assert.assertEquals(airlinesInformationList.size(), 3, "Information hasn't present.");
    }
}
