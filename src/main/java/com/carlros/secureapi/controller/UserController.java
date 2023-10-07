package com.carlros.secureapi.controller;

import com.carlros.secureapi.auth.JwtService;
import com.carlros.secureapi.model.User;
import com.carlros.secureapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final JwtService jwtService;

    @GetMapping("/users")
    public List<User> readAll(){
        return userService.all();
    }

    @GetMapping("/users/{idOrEmail}")
    public User readOne(@PathVariable String idOrEmail) {
        try {
            Long id = Long.parseLong(idOrEmail);
            return userService.one(id);
        } catch (NumberFormatException e) {
            return userService.one(idOrEmail);
        }
    }

    @GetMapping("users/me")
    public User readAuthenticated(@RequestAttribute("jwt") String jwt){
        String email = jwtService.extractUsername(jwt);
        return userService.one(email);
    }
}
