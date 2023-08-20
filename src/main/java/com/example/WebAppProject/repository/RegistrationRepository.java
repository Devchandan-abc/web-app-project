package com.example.WebAppProject.repository;
import com.example.WebAppProject.entity.Registration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {
	
	Optional<Registration> findByUserName(String userName);


}
