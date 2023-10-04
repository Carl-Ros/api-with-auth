package com.carlros.secureapi.controller;

import com.carlros.secureapi.model.User;
import com.carlros.secureapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService service;
    @GetMapping("/users")
    public List<User> readAll(){
        return service.all();
    }

    @GetMapping("/users/{idOrEmail}")
    public User readOne(@PathVariable String idOrEmail) {
        try {
            Long id = Long.parseLong(idOrEmail);
            return service.one(id);
        } catch (NumberFormatException e) {
            return service.one(idOrEmail);
        }
    }

}
