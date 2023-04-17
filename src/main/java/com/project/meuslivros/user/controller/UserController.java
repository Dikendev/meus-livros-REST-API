package com.project.meuslivros.user.controller;

import com.project.meuslivros.user.data.UserDto;
import com.project.meuslivros.user.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.UUID;


@AllArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @GetMapping("/api/v1/users")
    public Iterable<UserDto> getUsers() {
        return userService.findAllUsers();
    }

}
