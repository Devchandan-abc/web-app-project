package com.example.WebAppProject.controller;
import com.example.WebAppProject.service.ServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class ImageDetailsController {
	@Autowired
	private ServiceInterface serviceInterface;
	@PostMapping("/image")
	 public ResponseEntity<String> uploadImage(@RequestParam("image") MultipartFile file, @RequestParam(name = "id", required = false, defaultValue = "0") long id) throws IOException
	 {
		 return new ResponseEntity<>(serviceInterface.uplaoadImage(file,id),HttpStatus.CREATED);
	 }
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteImage(@PathVariable("id") long id)
	{
		return new ResponseEntity<>(serviceInterface.deleteImage(id),HttpStatus.OK);
	}
	
				

	}
	 
