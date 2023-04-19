package com.project.meuslivros.jwt.controller;

import com.project.meuslivros.jwt.models.AuthenticationRequest;
import com.project.meuslivros.jwt.models.AuthenticationResponse;
import com.project.meuslivros.jwt.service.ApplicationUserDetailsService;
import com.project.meuslivros.jwt.util.JwtUtil;
import com.project.meuslivros.user.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AuthenticateController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtTokenUtil;
    private final ApplicationUserDetailsService userDetailsService;

    @RequestMapping(value = "/authenticate")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthenticationResponse authenticate(@RequestBody AuthenticationRequest request)
            throws Exception {

        UserEntity user;

        try {
           user = userDetailsService.authenticate(request.getEmail(), request.getPassword()) ;

        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        var userDetails = userDetailsService.loadUserByUsername(user.getEmail());
        System.out.println(userDetails);

        var jwt = jwtTokenUtil.generateToken(userDetails);

        return new AuthenticationResponse(jwt);
    }

}
