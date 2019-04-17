package com.group3.budgetApp.services;

import com.group3.budgetApp.exceptions.ResourceNotFound;
import com.group3.budgetApp.model.Profile;
import com.group3.budgetApp.repository.ProfileRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import static org.mockito.Mockito.*;


@SpringBootTest
public class ProfileServicesTest {

    @Mock
    private ProfileRepository mockRepo;
    @InjectMocks
    private ProfileServices services;

    @Before
    public void setup(){
        mockRepo = mock(ProfileRepository.class);
        services = new ProfileServices(mockRepo);
    }

    @Test
    public void testCreate(){
        Profile profile = new Profile("Sean", "Rowan", "SpringKing");
        profile.setId(1);
        Profile expected = new Profile("Sean", "Rowan", "SpringKing");
        expected.setId(1);
        //Verify that create method is being called;
        when(mockRepo.save(profile)).thenReturn(expected);

        //Verify result
        Profile actual = services.createUser(profile);

        Assert.assertEquals(expected, actual);
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




}