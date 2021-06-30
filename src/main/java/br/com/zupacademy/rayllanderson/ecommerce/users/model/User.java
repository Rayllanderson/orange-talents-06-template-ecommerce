package br.com.zupacademy.rayllanderson.ecommerce.users.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

@Entity(name = "users")
public class User implements UserDetails {

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

    private String roles;

    @Deprecated
    private User() {}

    /**
     * @param login precisa vir em formato de email válido.
     * @param password precisa vir em texto limpo.
     */
    public User(String login, String password) {
        this.login = login;
        this.password = new BCryptPasswordEncoder().encode(password);
        this.roles = "ROLE_USER";
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(roles == null) return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
        return Arrays.stream(roles.split(",")).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
