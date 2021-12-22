package com.solvd.airport;

import com.solvd.airport.domain.Passenger;
import com.solvd.airport.service.AddressService;
import com.solvd.airport.service.PassengerService;
import com.solvd.airport.service.impl.AddressServiceImpl;
import com.solvd.airport.service.impl.PassengerServiceImpl;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

public class VerificationDataBaseDelete {

    @BeforeMethod
    public void beforeSelectTest() {
        //connect to DB, get connection from pool
         System.out.println("New delete test to start...");
    }

    @AfterMethod
    public void afterSelectTest() {
        //disconnect, return connection
         System.out.println("Delete test was finish.");
    }

    @Test(groups = {"delete", "passenger"})
    public void verifyDeletePassenger() {
        SoftAssert sa = new SoftAssert();
        Passenger passengerExample = new Passenger("Gavin", "White");
        PassengerService passengerService = new PassengerServiceImpl();
        passengerService.deleteFromPassengersList(passengerExample.getName(), passengerExample.getSurname())
                .forEach(passenger -> sa.assertNotEquals(passenger, passengerExample, "Person hasn't been deleted."));
        sa.assertAll();
    }

    @Parameters({"country", "locality"})
    @Test(groups = {"delete", "address"})
    public void verifyDeleteAddress(String country, String locality) {
        AddressService addressService = new AddressServiceImpl();
        addressService.deleteFromAddresses(country, locality);
        Assert.assertEquals(country, addressService.getLastAddress().getCountry(), "Deleting was failed");
    }
}
