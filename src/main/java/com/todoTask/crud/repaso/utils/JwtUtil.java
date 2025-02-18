package com.todoTask.crud.repaso.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;


import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class JwtUtil {

    @Value("${security.jwt.private}")
    private String jwtPrivateKey;

    @Value("${security.jwt.user.generator}")
    private String jwtUserGenerator;

    public String createToken(Authentication authentication) {

        Algorithm algorithm = Algorithm.HMAC256(jwtPrivateKey);

        String email = authentication.getPrincipal().toString();

        String authorities = authentication.getAuthorities().stream()
                .map(grantedAuthority -> grantedAuthority.getAuthority())
                .collect(Collectors.joining(","));

        String token = JWT.create()
                .withIssuer(this.jwtUserGenerator)
                .withSubject(email)
                .withClaim("authorities", authorities)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis()+180000))
                .withJWTId(UUID.randomUUID().toString())
                .withNotBefore(new Date(System.currentTimeMillis()))
                .sign(algorithm);
        return token;
    }

    public DecodedJWT validateToken(String token) {
        try{
            Algorithm algorithm = Algorithm.HMAC256(jwtPrivateKey);

            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(this.jwtUserGenerator).build();

            DecodedJWT jwt = verifier.verify(token);
            return jwt;
        }catch (JWTVerificationException e){
            throw new JWTVerificationException("Invalid JWT");
        }
    }

    public String getEmail(DecodedJWT token) {
        return token.getSubject().toString();
    }

    public Claim getSpecificClamin(DecodedJWT decodedJWT, String claimName){
        return decodedJWT.getClaim(claimName);
    }


    public Map<String, Claim> getAllClaims(DecodedJWT decodedJWT){
        return decodedJWT.getClaims();
    }
}
