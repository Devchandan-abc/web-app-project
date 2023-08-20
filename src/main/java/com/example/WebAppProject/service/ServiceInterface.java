package com.example.WebAppProject.service;

import com.example.WebAppProject.entity.ImageDetails;
import com.example.WebAppProject.entity.Registration;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ServiceInterface {

	Registration saveUser(Registration registration);

	String uplaoadImage(MultipartFile file, long id) throws IOException;

	List<ImageDetails> loginUser(Registration registration);

	String deleteImage(long id);


}
