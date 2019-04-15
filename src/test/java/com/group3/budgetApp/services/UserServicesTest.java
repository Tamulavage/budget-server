package com.group3.budgetApp.services;

import com.group3.budgetApp.model.User;
import com.group3.budgetApp.repository.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;


@SpringBootTest
public class UserServicesTest {

    @Mock
    private UserRepository mockRepo;
    @InjectMocks
    private UserServices services;

    @Before
    public void setup(){
        mockRepo = mock(UserRepository.class);
        services = new UserServices(mockRepo);
    }

    @Test
    public void testCreate(){
        User user = new User("Sean", "Rowan", "SpringKing");
        user.setId(1);
        User expected = new User("Sean", "Rowan", "SpringKing");
        expected.setId(1);
        //Verify that create method is being called;
        when(mockRepo.save(user)).thenReturn(expected);

        //Verify result
        User actual = services.createUser(user);
        System.out.println(actual);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testFindById(){
        User expected = new User("Sean", "Rowan","SpringKing");
        //When
        when(mockRepo.findById(1)).thenReturn(java.util.Optional.of(expected));
        User actual = services.findById(1);
        //Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testFindByLastName(){
        User expected = new User("Sean", "Rowan","SpringKing");
        List<User> expectedList = new ArrayList<>();
        expectedList.add(expected);
        //When
        List<User> actual = services.findAllByLast("Rowan");
        //Then
        verify(mockRepo, times(1)).findAllByLastName("Rowan");
    }

    @Test
    public void testDelete(){
        int id = 1;

        services.deleteUser(id);

        verify(mockRepo, times(1)).deleteById(id);
    }

    @Test
    public void testFindByUsername(){
        User expected = new User("Sean", "Rowan","SpringKing");
        //When
        when(mockRepo.findByUserName("SpringKing")).thenReturn(expected);
        User actual = services.findByUsername("SpringKing");
        //Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testFindAll(){
        List<User> list = services.findAll();

        verify(mockRepo, times(1)).findAll();
    }

    @Test
    public void testFindByFullName(){
        User expected = new User("Sean", "Rowan","SpringKing");
        when(mockRepo.findByFirstNameAndLastName("Sean", "Rowan")).thenReturn(expected);
        User actual = services.findByFullName(expected.getFirstName(), expected.getLastName());
        //Then
        Assert.assertEquals(expected, actual);
    }

//    @Test
//    public void testUpdate() {
//        User user = new User("Sean", "Rowan", "SpringKing");
//        user.setId(1);
//        User expected = new User("Bill", "Broan", "SpringThing");
//        expected.setId(1);
//        //Verify that create method is being called;
//        when(mockRepo.save(user)).thenReturn(expected);
//        System.out.println();
//        //Verify result
//        User actual = services.createUser(user);
//        //User theactual = services.updateUser(user,1);
//
//        System.out.println(services.findByUsername("SpringThing"));
//
//        Assert.assertEquals(expected, actual);
//    }

}