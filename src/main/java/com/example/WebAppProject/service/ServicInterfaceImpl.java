package com.example.WebAppProject.service;
import com.example.WebAppProject.entity.ImageDetails;
import com.example.WebAppProject.entity.Registration;
import com.example.WebAppProject.exception.FileNotFoundException;
import com.example.WebAppProject.repository.ImageDetailsRepository;
import com.example.WebAppProject.repository.RegistrationRepository;
import com.example.WebAppProject.util.ImageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class ServicInterfaceImpl implements ServiceInterface {
@Autowired	
private RegistrationRepository registrationRepo;
@Autowired	
private ImageDetailsRepository imageDetailsRepo;


	@Override
	public Registration saveUser(Registration registration) {
//		if(registration.getName()==null||registration.getUserName()==null||registration.getPassword()==null)
//			throw new FileNotFoundException("Field should not be empty");
		Optional<Registration> findByUserName = registrationRepo.findByUserName(registration.getUserName());
		if(findByUserName.isPresent())
		{
			throw new FileNotFoundException("User name Already Exists!!.");
		}
		else
		return registrationRepo.save(registration);
	}

	@Override
	public String uplaoadImage(MultipartFile file, long id) throws IOException {
		if (file.isEmpty()) {
				throw new FileNotFoundException("File is empty.");
			}
			if (file.getSize() > 3 * 1024 * 1024) { // 3 MB
				throw new FileNotFoundException("File size exceeds the limit.");
			}
			else {
				if (id==0) {
					String contentType = file.getContentType();
					if (!contentType.startsWith("image/") && !contentType.startsWith("application/pdf")) {
						throw new FileNotFoundException("Extension Not Supported!!");
					} else {
						ImageDetails imageDetails = new ImageDetails();
						imageDetails.setFileName(file.getOriginalFilename());
						imageDetails.setContent(file.getContentType());
						imageDetails.setImage(ImageUtil.compressImage(file.getBytes()));
						imageDetails.setSize(String.valueOf(file.getSize()));
						imageDetails.setTime(LocalDateTime.now());
						ImageDetails save = imageDetailsRepo.save(imageDetails);
						if (save != null) {
							return "file uploaded successfully : " + file.getOriginalFilename();
						}
					}
				} else {
					Optional<Registration> registration = registrationRepo.findById(id);
					Optional<ImageDetails> byId = imageDetailsRepo.findById(id);
					int sizeById = Integer.parseInt(byId.get().getSize());
					if (sizeById <=20 * 1024 * 1024) {
						sizeById = (int) (sizeById + file.getSize());
						String contentType = file.getContentType();
						if (!contentType.startsWith("image/") && !contentType.startsWith("application/pdf")) {
							throw new FileNotFoundException("Extension Not Supported!!");
						} else {
							byId.get().setFileName(file.getOriginalFilename());
							byId.get().setContent(file.getContentType());
							byId.get().setImage(ImageUtil.compressImage(file.getBytes()));
							byId.get().setSize(String.valueOf(sizeById));
							byId.get().setTime(LocalDateTime.now());
							byId.get().setUser(registration.get());
							ImageDetails save = imageDetailsRepo.save(byId.get());
							if (save != null) {
								return "file uploaded successfully : " + file.getOriginalFilename();
							}
						}
					} else
						throw new FileNotFoundException("Max Request size is 20MB!!");
				}
			}
			return null;
		}

	@Override
	public List<ImageDetails> loginUser(Registration registration) {
		Optional<Registration> findByUserName = registrationRepo.findByUserName(registration.getUserName());
		if(findByUserName.isPresent())
		{
			return imageDetailsRepo.findAll();
		     
		}
		else
			throw new FileNotFoundException("UserName or password is incorrect!!");
	}

	@Override
	public String deleteImage(long id) {
		imageDetailsRepo.deleteById(id);
		return "Deleted Succesfully";
	}

	
}


