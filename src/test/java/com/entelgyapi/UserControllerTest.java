package com.entelgyapi;

import com.entelgyapi.controller.UserController;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.logging.Logger;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:test.properties")
public class UserControllerTest {
  @Autowired
  private UserController userController;
  
  @Test
  public void testGetData(){
    HashMap dataResponse = this.userController.getUsers();
    Assert.assertTrue(dataResponse.containsKey("data"));
    Logger.getLogger(String.valueOf(this.getClass())).info(">>>>>>> message: "+this.userController.getApiUrlTest());
  }
}
