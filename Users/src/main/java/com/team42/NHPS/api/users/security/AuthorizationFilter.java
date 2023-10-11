package com.team42.NHPS.api.users.security;


import io.jsonwebtoken.*;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;

public class AuthorizationFilter extends BasicAuthenticationFilter {

    private Environment env;

    public AuthorizationFilter(AuthenticationManager authenticationManager, Environment env) {
        super(authenticationManager);
        this.env = env;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String authorizationHeader = req.getHeader(env.getProperty("authorization.token.header.name"));

        if (authorizationHeader == null || !authorizationHeader.startsWith(env.getProperty("authorization.token.header.prefix"))) {
            return;
        }

        UsernamePasswordAuthenticationToken authenticationToken = getAuthentication(req);

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(req, res);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest req) {
        String authorizationHeader = req.getHeader(env.getProperty("authorization.token.header.name"));
        if (authorizationHeader == null) return null;
        String token = authorizationHeader.split(" ")[1].trim();
        String tokenSecret = env.getProperty("token.secret");
        if (tokenSecret == null) return null;

        byte[] secretKeyBytes = Base64.getEncoder().encode(tokenSecret.getBytes());
        SecretKey secretKey = new SecretKeySpec(secretKeyBytes, SignatureAlgorithm.HS512.getJcaName());

        JwtParser jwtParser = Jwts.parserBuilder().setSigningKey(secretKey).build();

        Jwt<Header, Claims> jwt = jwtParser.parse(token);
        String userId = jwt.getBody().getSubject();

        if (userId == null) return null;

        return new UsernamePasswordAuthenticationToken(userId, null, new ArrayList<>());
    }
}
