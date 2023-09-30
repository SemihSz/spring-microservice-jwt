package com.food.recipe.social.api.service;

import com.food.recipe.social.api.model.JwtResponse;
import com.food.recipe.social.api.util.JwtTokenUtil;
import com.food.recipe.social.api.model.AuthLoginRequest;
import com.food.recipe.social.api.model.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.function.Function;

/**
 * Created by Semih, 2.07.2023
 */

@Service
@RequiredArgsConstructor
public class LoginService implements Function<AuthLoginRequest, JwtResponse> {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenUtil jwtTokenUtil;

    /**
     * This executable task process to login operations using with JWT token
     * @param authRequest the function argument
     * @return ${{@link JwtResponse} returning token information to user.}
     */
    @Override
    public JwtResponse apply(AuthLoginRequest authRequest) {

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String jwt = jwtTokenUtil.generateJwtToken(authentication);

        final UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail());
    }


}
