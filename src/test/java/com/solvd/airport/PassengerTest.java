package com.solvd.airport;

import com.solvd.airport.domain.Passenger;
import com.solvd.airport.service.PassengerService;
import com.solvd.airport.service.impl.PassengerServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class PassengerTest {

    private static final Logger LOGGER = LogManager.getLogger();

    private final String passengerName;
    private final String passengerSurname;

    @Parameters({"name", "surname"})
    public PassengerTest(String name, String surname) {
        this.passengerName = name;
        this.passengerSurname = surname;
    }

    @BeforeMethod
    public void beforeSelectTest() {
        //connect to DB, get connection from pool
        LOGGER.info("New delete test to start...");
    }

    @AfterMethod
    public void afterSelectTest() {
        //disconnect, return connection
        LOGGER.info("Delete test was finish.");
    }

    @BeforeGroups(groups = {"select"})
    public void beforeDeleteGroupTest() {
        LOGGER.info("Delete operations are running...");
    }

    @AfterGroups(groups = "select")
    public void afterDeleteGroupTest() {
        LOGGER.info("Delete operations was completed...");
    }

    @DataProvider(name = "passengerList")
    public Object[][] passengersList() {
        return new Object[][]{
                {"Edward", "Gregory"}, {"Gavin", "White"},
                {"Scott", "Simpson"},
                {"Aron", "Scot"}, {"Sandra", "Gardner"},
                {"Emma", "Richards"}, {"Anne", "Gordon"},
                {"Philomena", "Haynes"}, {"Joshua", "Cox"}
        };
    }

    @Test(groups = {"passenger", "insert"})
    public void verifyInsertPassengerTest() {
        Passenger passengerExample = new Passenger("Gavin", "White");
        PassengerService passengerService = new PassengerServiceImpl();
        passengerExample = passengerService.create(passengerExample);
        Assert.assertNotNull(passengerExample);
    }

    @Test(dataProvider = "passengerList", alwaysRun = true, groups = {"passenger", "insert  "})
    public void verifyDoubleInsertTest(String name, String surname) {
        Passenger passengerForInsert = new Passenger(name, surname);
        PassengerService passengerService = new PassengerServiceImpl();
        List<Passenger> passengersList = passengerService.getTickets();
        passengersList
                .forEach(passenger -> Assert.assertEquals(passenger, passengerForInsert, "Person is found."));
    }

    @Test(groups = {"delete", "passenger"})
    public void verifyDeletePassengerTest() {
        SoftAssert sa = new SoftAssert();
        Passenger passengerExample = new Passenger(passengerName, passengerSurname);
        PassengerService passengerService = new PassengerServiceImpl();
        List<Passenger> resultPassengersList = passengerService.deleteFromPassengersList(passengerName, passengerSurname);
        resultPassengersList
                .forEach(passenger -> sa.assertNotEquals(passenger, passengerExample, "Person hasn't been deleted."));
        sa.assertAll();
    }
}
