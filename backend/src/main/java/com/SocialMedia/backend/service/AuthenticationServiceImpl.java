package com.SocialMedia.backend.service;

import com.SocialMedia.backend.model.AppRole;
import com.SocialMedia.backend.model.AppUser;
import com.SocialMedia.backend.model.AuthenticationRequest;
import com.SocialMedia.backend.model.AuthenticationResponse;
import com.SocialMedia.backend.repository.UserRepository;
import com.SocialMedia.backend.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Autowired
    AuthenticationServiceImpl(UserRepository userRepository, JwtService jwtService,
                              PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public AuthenticationResponse createUser(AuthenticationRequest authenticationRequest) throws Exception {
        try {
            String username = authenticationRequest.getUsername();
            String password = passwordEncoder.encode(authenticationRequest.getPassword());
            AppRole role = AppRole.USER;
            AppUser user = new AppUser(username, password, role);
            userRepository.save(user);
            String jwtToken = generateToken(user);
            return new AuthenticationResponse(jwtToken);
        }
        catch (DataIntegrityViolationException e){
            throw new DataIntegrityViolationException("Username " + authenticationRequest.getUsername() +" already taken");
        }
        catch (Exception e) {
            throw new Exception("Internal Server Error");
        }


    }

    @Override
    public AuthenticationResponse loginUser(AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequest.getUsername(),
                            authenticationRequest.getPassword()
                    )
            );
            Optional<AppUser> appUser = userRepository.findByUsername(authenticationRequest.getUsername());
            if (appUser.isEmpty()){
                throw new UsernameNotFoundException("No user with credentials found");
            }
            else {
                String token = generateToken(appUser.get());
                return new AuthenticationResponse(token);

            }
        }
        catch (BadCredentialsException e){
            throw new BadCredentialsException("Credentials are invalid");
        }
        catch (Exception e){
            throw new Exception("Internal Server Error");
        }
    }

    private String generateToken(AppUser user){
        return jwtService.generateToken(user);
    }
}
