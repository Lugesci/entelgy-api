package com.entelgyapi.controller;

import com.entelgyapi.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("users")
public class UserController {
  @Value("${spring.external-api.url}")
  private String externalApiUrl;
  
  @Autowired
  private UserService userService = new UserService();
  
  @PostConstruct
  public void constructor(){
    Logger.getLogger(String.valueOf(this.getClass())).info(">>>>>> UserController::constructor()");
  }
  
  @PostMapping("/list")
  public HashMap getUsers() {
    ArrayList<String> usersList = new ArrayList();
    HashMap returnedMap = new HashMap();
    
    try {
      List<HashMap<String, String>> dataResponse;
      HashMap jsonResult = userService.getUsers();
  
      dataResponse = (List<HashMap<String, String>>) jsonResult.get("data");
      dataResponse.forEach(user -> {
        Object idUser, lastNameUser, emailUser;
        idUser = user.get("id");
        lastNameUser = user.get("last_name");
        emailUser = user.get("email");
    
        usersList.add(idUser+"|"+lastNameUser+"|"+emailUser);
      });
      
    }catch (JsonProcessingException e) {
      returnedMap.put("errorMessage", "Ocurrió un error al obtener los datos de la API externa:"+e.getMessage());
    } catch (HttpClientErrorException e) {
      returnedMap.put("errorMessage", "La URL no es válida");
    }
  
    returnedMap.put("data", usersList);
    return returnedMap;
  }
  
  @PostConstruct
  public void destroy(){
    Logger.getLogger(String.valueOf(this.getClass())).info(">>>>>> UserController::destroy()");
  }
  
  //Testing
  public String getApiUrlTest() {
    return "UserController test | spring.external-api.url: "+this.externalApiUrl;
  }
}
