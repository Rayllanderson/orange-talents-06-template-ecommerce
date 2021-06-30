package br.com.zupacademy.rayllanderson.ecommerce.users.requests;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginInputRequest {

    private final String email;
    private final String password;

    public LoginInputRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public UsernamePasswordAuthenticationToken build() {
        return new UsernamePasswordAuthenticationToken(this.email, this.password);
    }
}
