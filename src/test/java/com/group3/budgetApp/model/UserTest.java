package com.group3.budgetApp.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {
    private User user;

    @Before
    public void setUp() {
        user = new User();
    }

    @Test
    public void setId() {
        Assert.assertNull(user.getId());
    }

    @Test
    public void setName() {
        //Given
        String expectedFirst = "Sean";
        String expectedLast = "Rowan";

        //When
        user.setFirstName(expectedFirst);
        user.setLastName(expectedLast);

        //Then
        Assert.assertEquals(expectedFirst, user.getFirstName());
        Assert.assertEquals(expectedLast, user.getLastName());
    }
}