package com.example.pweb.service;

import com.example.pweb.dto.ReqRes;
import com.example.pweb.exceptions.CredentialsAlreadyExistException;
import com.example.pweb.exceptions.UnexpectedException;
import com.example.pweb.persistance.models.OurUsers;
import com.example.pweb.persistance.models.Role;
import com.example.pweb.persistance.repositories.OurUsersRepository;
import com.example.pweb.persistance.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class AuthService {

    @Autowired
    private OurUsersRepository ourUsersRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private JWTUtils jwtUtils;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;

    public ReqRes signUp(ReqRes registrationRequest) {
        ReqRes response = new ReqRes();

        checkRegisterDetails(registrationRequest);

        if(ourUsersRepository.existsByEmail(registrationRequest.getEmail())){
            throw new CredentialsAlreadyExistException("Email already exists!");
        }

        try {
            OurUsers ourUsers = new OurUsers();
            ourUsers.setEmail(registrationRequest.getEmail());
            ourUsers.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
            Optional<Role> role = roleRepository.findById(registrationRequest.getRole());
            role.ifPresent(ourUsers::setRole);
            OurUsers ourUserResult = ourUsersRepository.save(ourUsers);
            if(ourUserResult.getId() > 0) {
                response.setOurUser(ourUserResult);
                response.setMessage("User registered successfully");
                response.setStatusCode(200);
            }
        } catch (Exception e) {
            throw new UnexpectedException("Unexpected error occurred!: " + e.getMessage());
        }

        return response;
    }

    public ReqRes signIn(ReqRes signInRequest) {
        ReqRes response = new ReqRes();

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getEmail(), signInRequest.getPassword()));
            var user = ourUsersRepository.findByEmail(signInRequest.getEmail()).orElseThrow();
            System.out.println("User is: " + user);
            var jwt = jwtUtils.generateToken(user);
            var refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), user);
            response.setStatusCode(200);
            response.setToken(jwt);
            response.setRefreshToken(refreshToken);
            response.setExpirationTime("24h");
            response.setMessage("User signed in successfully");
        } catch (Exception e) {
            throw new UnexpectedException("Unexpected error occurred!: " + e.getMessage());
        }

        return response;
    }

    public ReqRes refreshToken(ReqRes refreshTokenRequest) {
        ReqRes response = new ReqRes();
        String ourEmail = jwtUtils.extractUsername(refreshTokenRequest.getRefreshToken());
        OurUsers ourUser = ourUsersRepository.findByEmail(ourEmail).orElseThrow();
        if(jwtUtils.isTokenValid(refreshTokenRequest.getToken(), ourUser)) {
            var jwt = jwtUtils.generateToken(ourUser);
            response.setStatusCode(200);
            response.setToken(jwt);
            response.setRefreshToken(refreshTokenRequest.getToken());
            response.setExpirationTime("24h");
            response.setMessage("Token refreshed successfully");
        } else {
            throw new UnexpectedException("Invalid token");
        }

        return response;
    }

    void checkRegisterDetails(ReqRes registerReq) {
        if(registerReq.getEmail() == null || registerReq.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email is empty!");
        } else if(validateEmail(registerReq.getEmail())) {
            throw new IllegalArgumentException("Email is invalid!");
        } else if(registerReq.getPassword() == null || registerReq.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password is empty!");
        }
    }

    boolean validateEmail(String email) {
        return Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\\\.[A-Za-z0-9-]+)*(\\\\.[A-Za-z]{2,})$")
                .matcher(email)
                .matches();
    }
}
