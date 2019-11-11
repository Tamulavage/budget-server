package com.group3.budgetApp.model;

import org.junit.Assert;
import org.junit.Test;

public class ProfileTest {

    @Test
    public void setName() {
        //Given
        String expectedFirst = "S";
        String expectedLast = "R";
        String expectedUserName = "SpringKing";

        //When
        Profile profile = new Profile(expectedFirst, expectedLast, expectedUserName);
        profile.setId(55);

        //Then
        Assert.assertEquals(expectedFirst, profile.getFirstName());
        Assert.assertEquals(expectedLast, profile.getLastName());
    }
}