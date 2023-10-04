package com.carlros.secureapi.service;

import com.carlros.secureapi.exception.EntityNotFoundException;
import com.carlros.secureapi.model.User;
import com.carlros.secureapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    public User one(Long id){
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format("User with id %s does not exist.", id)));
    }

    public User one(String email){
        return repository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException(String.format("User with email %s does not exist.", email)));
    }

    public List<User> all(){
        return repository.findAll();
    }
}
