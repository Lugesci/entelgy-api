package com.entelgyapi;

import com.entelgyapi.controller.UserController;
import com.entelgyapi.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:test.properties")
public class UserServiceTest {
  
  @Autowired
  private UserController userController = new UserController();
  
  @InjectMocks
  private UserService userServiceMock = Mockito.mock(UserService.class);
  
  private HashMap response = new HashMap();
  
  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    String[] users= {"1|Esquen|augusto.esquen2@gmail"};
    response.put("data", users);
  
    try {
      Mockito.when(userServiceMock.getUsers()).thenReturn(response);
    } catch (Exception e){
    }
  }
  
  @Test
  public void getUsers(){
    Assert.assertNotNull(userController.getUsers());
  }
}
