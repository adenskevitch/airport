package com.solvd.airport;

import com.solvd.airport.domain.Passenger;
import com.solvd.airport.service.AddressService;
import com.solvd.airport.service.PassengerService;
import com.solvd.airport.service.impl.AddressServiceImpl;
import com.solvd.airport.service.impl.PassengerServiceImpl;
import org.testng.Assert;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerificationDataBaseDelete {

    @BeforeGroups(groups = "delete")
    public void beforeDeleteGroupTest(){
        System.out.println("Delete operations are running...");
    }

    @AfterGroups(groups = "delete")
    public void afterDeleteGroupTest(){
        System.out.println("Delete operations was completed...");
    }

    @Test(groups = {"delete","passenger"})
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
    public void verifyDeleteAddress(String country, String locality){
        AddressService addressService = new AddressServiceImpl();
        addressService.deleteFromAddresses(country, locality);
        Assert.assertEquals(country, addressService.getLastAddress().getCountry(), "Deleting was failed");
    }
}
