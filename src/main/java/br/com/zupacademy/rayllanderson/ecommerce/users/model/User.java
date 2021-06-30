package br.com.zupacademy.rayllanderson.ecommerce.users.model;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank @Email
    @Column(nullable = false)
    private String login;

    @NotBlank @Size(min = 6)
    @Column(nullable = false)
    private String password;

    @PastOrPresent @NotNull
    private LocalDateTime registeredIn = LocalDateTime.now();

    @Deprecated
    private User() {}

    /**
     * @param login precisa vir em formato de email válido.
     * @param password precisa vir em texto limpo.
     */
    public User(String login, String password) {
        this.login = login;
        this.password = new BCryptPasswordEncoder().encode(password);
    }
}
