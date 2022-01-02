package com.wildannn.user.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wildannn.user.payload.ErrorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class SecurityEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper mapper;

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {

        response.setContentType("application/json");
        response.setStatus(401);
        ErrorResponse errorResponse  = ErrorResponse.builder()
                .message("Username and password required")
                .status(401)
                .build();
        response.getWriter()
                .print(mapper.writeValueAsString(errorResponse));
    }
}
