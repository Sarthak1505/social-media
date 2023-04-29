package com.SocialMedia.backend.service;

import com.SocialMedia.backend.model.AuthenticationRequest;
import com.SocialMedia.backend.model.AuthenticationResponse;
import org.springframework.stereotype.Service;


public interface AuthenticationService {

    AuthenticationResponse createUser(AuthenticationRequest authenticationRequest) throws Exception;

    AuthenticationResponse loginUser(AuthenticationRequest authenticationRequest) throws Exception;
}
