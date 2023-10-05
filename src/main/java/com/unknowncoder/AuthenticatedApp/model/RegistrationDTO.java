package com.unknowncoder.AuthenticatedApp.model;

import lombok.*;

public class RegistrationDTO {
    private String username;
    private String password;

    @Override
    public String toString() {
        return "Registration info: username: "+this.username+" password: "+this.password;
    }

    public String getUsername() {
        return this.username;
    }
    public String getPassword() {
        return this.password;
    }
}
