package com.group3.budgetApp.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {
    private User user;

    @Test
    public void setName() {
        //Given
        String expectedFirst = "Sean";
        String expectedLast = "Rowan";
        String expectedUserName = "SpringKing";

        //When
        User user = new User(expectedFirst, expectedLast, expectedUserName);
        user.setId(55);

        //Then
        Assert.assertEquals(expectedFirst, user.getFirstName());
        Assert.assertEquals(expectedLast, user.getLastName());

        System.out.println(user.toString());
    }
}