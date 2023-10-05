package com.unknowncoder.AuthenticatedApp.service;

import com.unknowncoder.AuthenticatedApp.model.Role;
import com.unknowncoder.AuthenticatedApp.model.User;
import com.unknowncoder.AuthenticatedApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("In user details service");
        return userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("user is not valid"));
//        if(!username.equals("Ethan")) throw new UsernameNotFoundException("Not Ethan");
//
//        Set<Role> roles = new HashSet<>();
//        roles.add(new Role(1,"USER"));
//        System.out.println(username);
//        return new User(1, "Ethan",encoder.encode("password"), roles);
    }
}
