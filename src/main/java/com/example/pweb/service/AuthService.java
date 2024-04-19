package com.example.pweb.service;

import com.example.pweb.dto.ReqRes;
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
        try {
            OurUsers ourUsers = new OurUsers();
            ourUsers.setEmail(registrationRequest.getEmail());
            ourUsers.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
            Optional<Role> role = roleRepository.findById(registrationRequest.getRole());
            role.ifPresent(ourUsers::setRole);
            OurUsers ourUserResult = ourUsersRepository.save(ourUsers);
            if(ourUserResult != null && ourUserResult.getId() > 0) {
                response.setOurUser(ourUserResult);
                response.setMessage("User registered successfully");
                response.setStatusCode(200);
            }
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setError(e.getMessage());
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
            response.setStatusCode(500);
            response.setError(e.getMessage());
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
            response.setStatusCode(500);
            response.setError("Invalid token");
        }

        return response;
    }
}
