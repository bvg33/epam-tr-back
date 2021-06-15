package com.epam.tr.controller;

import com.epam.tr.dao.entities.AppUser;
import com.epam.tr.exceptions.InvalidCredentialsException;
import com.epam.tr.security.jwt.JWTProvider;
import com.epam.tr.service.SecurityUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import static java.util.Objects.isNull;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private SecurityUserDetailService userDetailService;
    @Autowired
    private JWTProvider jwtProvider;
    @Autowired
    private BCryptPasswordEncoder encoder;
    private static final String INVALID_CREDENTIALS = "Invalid Credentials";

    @PostMapping
    public ResponseEntity<String> auth(@RequestBody AppUser user) throws InvalidCredentialsException {
        UserDetails userDetails = userDetailService.loadUserByUsername(user.getLogin());
        if (!isNull(userDetails) && encoder.matches(user.getPassword(), userDetails.getPassword())) {
            String token = jwtProvider.generateToken(userDetails.getUsername());
            return ResponseEntity.ok(token);
        } else {
            throw new InvalidCredentialsException(INVALID_CREDENTIALS);
        }
    }
}
