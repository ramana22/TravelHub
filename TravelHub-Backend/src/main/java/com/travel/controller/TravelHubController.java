package com.travel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.travel.model.User;
import com.travel.service.TravelHubService;

@RestController
public class TravelHubController {
	 @Autowired
	 TravelHubService service;
	 @PostMapping("/register")
     public User registerUser(@RequestBody User user) throws Exception  {
  	   String tempemail=user.getEmail();
  	   if(tempemail!=null && !"".equals(tempemail)) {
  		   User userobj=service.fetchemail(tempemail);
  		   if(userobj!=null) {
  			   throw new Exception("user with "+tempemail+" already exits");
  		   }
  	   }
  	   User userobj=null;
  	   if(user != null) {
  		   userobj=service.saveUser(user);
  	   }
  	   return userobj;
    }
	@GetMapping("/health")
    public String healthCheck() {
        return "Application is running!";
    }
}
