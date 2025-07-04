package dev.unionrobotics.server.methods.auth;

import dev.unionrobotics.server.Client;
import dev.unionrobotics.server.Errors;
import dev.unionrobotics.server.methods.Method;

import java.util.Objects;
import java.util.UUID;

public class AuthenticationByPassword extends Method {
    private String user;
    private String password;

    public AuthenticationByPassword(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public boolean authenticate() {
        return user.equals("admin") && password.equals("admin");
    }

    @Override
    public String call(Client client) {
        if(Objects.isNull(user) || Objects.isNull(password)) return Errors.incorrectAuth.toString();
        return authenticate() ? "Authenticated" : Errors.incorrectAuth.toString();
    }

    @Override
    public String toString() {
        return "AuthenticationByPassword{" +
                "user='" + user + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
