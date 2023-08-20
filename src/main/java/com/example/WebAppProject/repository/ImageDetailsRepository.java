package com.example.WebAppProject.repository;


import com.example.WebAppProject.entity.ImageDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageDetailsRepository extends JpaRepository<ImageDetails, Long> {
	
	Optional<ImageDetails> findByFileName(String fileName);

}
