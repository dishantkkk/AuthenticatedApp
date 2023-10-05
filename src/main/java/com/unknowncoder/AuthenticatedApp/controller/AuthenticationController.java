package com.unknowncoder.AuthenticatedApp.controller;

import com.unknowncoder.AuthenticatedApp.model.LoginResponseDTO;
import com.unknowncoder.AuthenticatedApp.model.RegistrationDTO;
import com.unknowncoder.AuthenticatedApp.model.User;
import com.unknowncoder.AuthenticatedApp.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public User registerUser(@RequestBody RegistrationDTO data) {
        return authenticationService.registerUser(data.getUsername(),data.getPassword());
    }

    @PostMapping("/login")
    public LoginResponseDTO loginUser(@RequestBody RegistrationDTO registrationDTO) {
        return authenticationService.loginUser(registrationDTO.getUsername(), registrationDTO.getPassword());
    }
}
