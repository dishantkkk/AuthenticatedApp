package com.unknowncoder.AuthenticatedApp.repository;

import com.unknowncoder.AuthenticatedApp.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleReporitory extends JpaRepository<Role, Integer> {

    Optional<Role> findByAuthority(String authority);
}
