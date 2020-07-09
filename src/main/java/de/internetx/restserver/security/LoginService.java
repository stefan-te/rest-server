package de.internetx.restserver.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import de.internetx.restserver.Constants;
import de.internetx.restserver.RestServerApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class LoginService {

    private static final Logger log = LoggerFactory.getLogger(RestServerApplication.class);


    public ResponseEntity<String> login(Authentication auth) {
        log.info("Login");
        User user = (User) auth.getPrincipal();
        if (user != null) {
            return new ResponseEntity<>(generateJWTToken(user.getUsername()), HttpStatus.OK);
        }
        return new ResponseEntity<>("Login failed", HttpStatus.BAD_REQUEST);
    }

    private String generateJWTToken(String username) {
        return JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + Constants.EXPIRATION_DATE))
                .sign(Algorithm.HMAC256(Constants.SECRET));
    }
}
