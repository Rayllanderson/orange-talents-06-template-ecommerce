package br.com.zupacademy.rayllanderson.ecommerce.users.requests;

import br.com.zupacademy.rayllanderson.ecommerce.users.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserPostRequest {

    @NotBlank @Email
    private final String login;

    @NotBlank @Size(min = 6)
    private final String password;

    public UserPostRequest(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public User toModel(PasswordEncoder encoder){
        return new User(login, encoder.encode(password));
    }
}
