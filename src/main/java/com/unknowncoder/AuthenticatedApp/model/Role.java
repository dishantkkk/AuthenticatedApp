package com.unknowncoder.AuthenticatedApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    private Integer roleId;

    private String authority;

    public Role(String authority) {
        this.authority=authority;
    }
    public Role(Integer roleId, String authority) {
        this.roleId=roleId;
        this.authority=authority;
    }
    @Override
    public String getAuthority() {
        return this.authority;
    }
}
