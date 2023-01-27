package com.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class UserService {
@Autowired
    private  UserRepository userRepository;
    @Autowired
    private StudentRepository studentRepository;
   // @Autowired
//    public UserService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    
//    @Autowired
//    public StudentService(StudentRepository studentRepository) {
//        this.studentRepository = studentRepository;
//    }
    public void create(User user) {
        user = userRepository.save(user);
    }
}
