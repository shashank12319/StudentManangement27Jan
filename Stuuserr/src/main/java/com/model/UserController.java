package com.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {
	
	@Autowired
    private  UserRepository userRepository;
    @Autowired
    private StudentRepository studentRepository;
    @GetMapping("/")
    public String demo() {
		return "shashank";
    	
    }
	@PostMapping("/create")
	public ResponseEntity<User> createUserAndStudent(@RequestBody UserDTO userDTO) {
		try {
	    User user = new User();
	    user.setName(userDTO.getName());
	    user.setPassword(userDTO.getPassword());
	    if(userRepository.existsByName(userDTO.getName())) {
           throw new UserAlreadyExistException("User with this name already exist");
	    }
	    Student student = new Student();
	    student.setFirstName(userDTO.getFirstName());
	    student.setLastName(userDTO.getLastName());
	    student.setDateOfBirth(userDTO.getDateOfBirth());
	    student.setFatherName(userDTO.getFatherName());
	    student.setMotherName(userDTO.getMotherName());
	    student.setClassName(userDTO.getClassName());
	    student.setRollNo(userDTO.getRollNo());
	    student.setMobileNo(userDTO.getMobileNo());
	    student.setAdmissionDate(userDTO.getAdmissionDate());
	    
	    student = studentRepository.save(student);
	    if(student.getId()!=null) {
	    	user.setStudent(student);
	    	user = userRepository.save(user);
	    }
	    
	    return new ResponseEntity<>(user, HttpStatus.CREATED);
	
	    
    }  
		
		catch (DataIntegrityViolationException e) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
	}
}

