package com.wildannn.user.service.impl;

import com.wildannn.user.entity.User;
import com.wildannn.user.model.LoginModel;
import com.wildannn.user.model.TokenModel;
import com.wildannn.user.payload.TokenResponse;
import com.wildannn.user.repository.UserRepository;
import com.wildannn.user.security.JwtTokenProvider;
import com.wildannn.user.service.AuthService;
import com.wildannn.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User register(User req) {
        User user = userService.makeUser(req);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    @Override
    public TokenModel generateToken(LoginModel req) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            req.getUsername(),
                            req.getPassword()));

            SecurityContextHolder.getContext()
                    .setAuthentication(authentication);
            String jwt = tokenProvider.generateToken(authentication);

            return TokenModel.builder()
                    .token(jwt)
                    .build();

        } catch (BadCredentialsException ex) {
            log.error("Bad credential", ex);
            throw new RuntimeException(ex.getMessage(), ex);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            throw new RuntimeException(ex.getMessage(), ex);
        }
    }
}
