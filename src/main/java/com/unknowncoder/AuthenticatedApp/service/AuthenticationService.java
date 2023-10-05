package com.unknowncoder.AuthenticatedApp.service;

import java.util.HashSet;
import java.util.Set;

import com.unknowncoder.AuthenticatedApp.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unknowncoder.AuthenticatedApp.model.User;
import com.unknowncoder.AuthenticatedApp.model.LoginResponseDTO;
import com.unknowncoder.AuthenticatedApp.model.Role;
import com.unknowncoder.AuthenticatedApp.repository.RoleReporitory;
import com.unknowncoder.AuthenticatedApp.repository.UserRepository;

@Service
@Transactional
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleReporitory roleRepository;


    private PasswordEncoder passwordEncoder;

    private AuthenticationManager authenticationManager;

    private TokenService tokenService;
    public AuthenticationService(PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager=authenticationManager;
        this.passwordEncoder=passwordEncoder;
        this.tokenService=tokenService;
    }

    public User registerUser(String username, String password){

        String encodedPassword = passwordEncoder.encode(password);
        Role userRole = roleRepository.findByAuthority("USER").get();

        Set<Role> authorities = new HashSet<>();

        authorities.add(userRole);

        return userRepository.save(new User(0, username, encodedPassword, authorities));
    }

    public LoginResponseDTO loginUser(String username, String password){

        System.out.println(password);
        try{
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            String token = tokenService.generateJwt(auth);

            return new LoginResponseDTO(userRepository.findByUsername(username).get(), token);

        } catch(AuthenticationException e){
            return new LoginResponseDTO(null, "");
        }
    }

}