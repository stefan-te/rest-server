package de.internetx.restserver.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import de.internetx.restserver.Constants;
import de.internetx.restserver.RestServerApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class LoginController {

    private static final Logger log = LoggerFactory.getLogger(RestServerApplication.class);

    @PostMapping("/login")
    public String loginUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String token = JWT.create()
                .withSubject(((User) auth.getPrincipal()).getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + Constants.EXPIRATION_DATE))
                .sign(Algorithm.HMAC256(Constants.SECRET));

        return token;
    }
}
