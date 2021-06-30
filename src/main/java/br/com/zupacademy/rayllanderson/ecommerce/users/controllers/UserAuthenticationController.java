package br.com.zupacademy.rayllanderson.ecommerce.users.controllers;

import br.com.zupacademy.rayllanderson.ecommerce.core.conf.security.jwt.TokenManager;
import br.com.zupacademy.rayllanderson.ecommerce.users.reponses.LoginUserResponse;
import br.com.zupacademy.rayllanderson.ecommerce.users.requests.LoginInputRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class UserAuthenticationController {

    private final AuthenticationManager authManager;

    private final TokenManager tokenManager;

    @Autowired
    public UserAuthenticationController(AuthenticationManager authManager, TokenManager tokenManager) {
        this.authManager = authManager;
        this.tokenManager = tokenManager;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> authenticate(@RequestBody LoginInputRequest request) {
        UsernamePasswordAuthenticationToken authenticationToken = request.build();
        try {
            Authentication authentication = authManager.authenticate(authenticationToken);
            String jwt = tokenManager.generateToken(authentication);
            return ResponseEntity.ok(new LoginUserResponse(jwt));
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().body("Username ou password inválidos");
        }
    }
}
