package com.entelgyapi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.logging.Logger;

@Service
public class UserService {
  @Value("${spring.external-api.url}")
  private String externalApiUrl;
  
  RestTemplate restTemplate = new RestTemplate();
  
  @PostConstruct
  public void constructor(){
    Logger.getLogger(String.valueOf(this.getClass())).info(">>>>>> UserService::constructor()");
  }
  
  public HashMap getUsers() throws JsonProcessingException {
    HashMap jsonResult;
    jsonResult = restTemplate.getForObject(this.externalApiUrl, HashMap.class);
    return jsonResult;
  }
  
  @PostConstruct
  public void destroy(){
    Logger.getLogger(String.valueOf(this.getClass())).info(">>>>>> UserService::destroy()");
  }
  
  //Testing
  public String getApiUrlTest() {
    return "UserService test | spring.external-api.url: "+this.externalApiUrl;
  }
}
