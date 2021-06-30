package br.com.zupacademy.rayllanderson.ecommerce.core.conf.security;

import br.com.zupacademy.rayllanderson.ecommerce.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.NonUniqueResultException;

@Service("my-user-service")
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            return userRepository.findByLogin(username).orElseThrow(() -> new UsernameNotFoundException("Nenhum usuário encontrado com este email: "+ username));
        } catch (InternalAuthenticationServiceException e){
            throw new IllegalStateException("Existem mais de um usuário com este username: " + username);
        }
    }

}
