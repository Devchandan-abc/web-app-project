package com.example.WebAppProject.controller;


import com.example.WebAppProject.entity.ImageDetails;
import com.example.WebAppProject.entity.Registration;
import com.example.WebAppProject.service.ServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
@Controller
@RequiredArgsConstructor
public class RegistrationController {
	@Autowired
	private ServiceInterface serviceInterface;
	@PostMapping("/save")
	public ResponseEntity<Registration> saveUser(@RequestBody Registration registration)
	{
		return new ResponseEntity<>(serviceInterface.saveUser(registration),HttpStatus.CREATED);
	}
	@PostMapping("/login")
	public ResponseEntity<List<ImageDetails>> loginUser(@RequestBody Registration registration)
	{
		return new ResponseEntity<>(serviceInterface.loginUser(registration),HttpStatus.CREATED);
	}
	

}
