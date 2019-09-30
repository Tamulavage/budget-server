package com.group3.budgetApp.services;

import com.group3.budgetApp.exceptions.ResourceNotFound;
import com.group3.budgetApp.model.Profile;
import com.group3.budgetApp.repository.ProfileRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;


@SpringBootTest
public class ProfileServicesTest {

    @Autowired
    private ProfileServices services;
    @MockBean
    private ProfileRepository mockRepo;



    @Before
    public void setup(){
        mockRepo = mock(ProfileRepository.class);
        services = new ProfileServices(mockRepo);
    }

    @Test
    public void testCreate(){
        Profile profile = new Profile("S", "R", "username");
        Profile expected = new Profile("S", "R", "username");
        //Verify that create method is being called;
        when(mockRepo.save(profile)).thenReturn(expected);

        //Verify result
        Profile actual = services.createUser(profile);
        Assert.assertEquals(expected.toString(), actual.toString());
    }

    @Test
    public void testFindById() throws ResourceNotFound {
        Profile expected = new Profile("Sean", "Rowan","SpringKing");
        //When
        when(mockRepo.findById(1)).thenReturn(java.util.Optional.of(expected));
        Profile actual = services.findById(1);
        //Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testDelete(){
        int id = 1;

        services.deleteUser(id);

        verify(mockRepo, times(1)).deleteById(id);
    }

    @Test
    public void testFindByUsername(){
        //Given
        String username = "SpringKing";
        Profile expected = new Profile("Sean", "Rowan",username);
        //When
        when(mockRepo.findByUserName(username)).thenReturn(expected);
        Profile actual = services.findByUsername(username);
        //Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testByFullName(){
        //Given
        String first = "Sean";
        String last = "Rowan";
        Profile expected = new Profile(first, last,"SpringKing");
        //When
        when(mockRepo.findByFirstNameAndLastName(first, last)).thenReturn(expected);
        Profile actual = services.findByFullName(first, last);
        //Then
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void testFindAll(){
        //Given
        List<Profile> expected = new ArrayList<>();
        Profile profile1 = new Profile("Sean", "Rowan","SpringKing");
        Profile profile2 = new Profile("Jack", "Johnson","TheDude");
        expected.add(profile1);
        expected.add(profile2);
        //When
        when(mockRepo.findAll()).thenReturn(expected);
        List<Profile> actual = services.findAll();
        //Then
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void testFindByLast(){
        //Given
        List<Profile> expected = new ArrayList<>();
        String first = "Sean";
        String last = "Rowan";
        Profile profile = new Profile(first, last,"SpringKing");
        expected.add(profile);
        //When
        when(mockRepo.findAllByLastName(last)).thenReturn(expected);
        List<Profile> actual = services.findAllByLast(last);
        //Then
        Assert.assertEquals(expected, actual);
    }

 

    @Test(expected = ResourceNotFound.class)
    public void testResourceNotFound() throws ResourceNotFound {
        services.findById(99);
    }

    @Test
    public void testNullaryConstructor(){
        Assert.assertNotNull(new ProfileServices());
    }

    @Test
    public void testUpdateAccount() {
        String first = "Sean";
        String last = "Rowan";
        String username = "SpringKing";
        Profile profile = new Profile(first, last, username);
        Profile newPro = new Profile(first, last, "Testing");
        Mockito.when(mockRepo.findById(1)).thenReturn(Optional.of(profile));

        services.updateUser(newPro, 1);

        Mockito.verify(mockRepo, Mockito.times(1)).findById(1);
    }

}