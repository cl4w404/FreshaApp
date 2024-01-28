package com.devcorp.freshi.service;

import com.devcorp.freshi.models.User;

import java.util.List;
import java.util.Optional;

import com.devcorp.freshi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserService() {
    }

    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    public Optional<User> getSingleUser(String email) {
        return this.userRepository.findByEmail(email);
    }

    public User addUser(User user) {
        return (User)this.userRepository.save(user);
    }

}
