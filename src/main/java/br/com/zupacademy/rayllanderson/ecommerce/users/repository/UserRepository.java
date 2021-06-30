package br.com.zupacademy.rayllanderson.ecommerce.users.repository;

import br.com.zupacademy.rayllanderson.ecommerce.users.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByLogin(String login);
}
