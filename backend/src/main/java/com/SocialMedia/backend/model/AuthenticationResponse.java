package com.SocialMedia.backend.model;


public class AuthenticationResponse {

    private String jwtToken;

    public AuthenticationResponse() {
    }

    public AuthenticationResponse(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    @Override
    public String toString() {
        return "AuthenticationResponse{" +
                "jwtToken='" + jwtToken + '\'' +
                '}';
    }
}
