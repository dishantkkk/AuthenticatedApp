package com.unknowncoder.AuthenticatedApp;

import com.unknowncoder.AuthenticatedApp.model.Role;
import com.unknowncoder.AuthenticatedApp.model.User;
import com.unknowncoder.AuthenticatedApp.repository.RoleReporitory;
import com.unknowncoder.AuthenticatedApp.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class AuthenticatedAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthenticatedAppApplication.class, args);
	}

	@Bean
	CommandLineRunner run(RoleReporitory roleRepository, UserRepository userRepository, PasswordEncoder passwordEncode){
		return args ->{
			if(roleRepository.findByAuthority("ADMIN").isPresent()) return;
			Role adminRole = roleRepository.save(new Role("ADMIN"));
			roleRepository.save(new Role("USER"));

			Set<Role> roles = new HashSet<>();
			roles.add(adminRole);

			User admin = new User(1, "admin", passwordEncode.encode("password"), roles);

			userRepository.save(admin);
		};
	}

}
