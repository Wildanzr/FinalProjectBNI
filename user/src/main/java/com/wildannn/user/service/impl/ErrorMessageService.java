package com.wildannn.user.service.impl;

import com.wildannn.user.handler.ErrorMessage;
import com.wildannn.user.payload.ErrorResponse;
import org.springframework.stereotype.Service;

@Service
public class ErrorMessageService {

    public ErrorResponse errorDefinition(Exception ex) {
        ErrorResponse response = ErrorResponse.builder()
                .message("Internal Server Error")
                .status(500)
                .build();

        if(ex.getMessage().equalsIgnoreCase(ErrorMessage.NOT_FOUND))
            return errorModifier(response, ErrorMessage.NOT_FOUND, 404);

        else if(ex.getMessage().equalsIgnoreCase(ErrorMessage.EMAIL_REGISTERED))
            return errorModifier(response, ErrorMessage.EMAIL_REGISTERED, 400);

        else if(ex.getMessage().equalsIgnoreCase(ErrorMessage.USERNAME_REGISTERED))
            return errorModifier(response, ErrorMessage.USERNAME_REGISTERED, 400);

        else if(ex.getMessage().equalsIgnoreCase(ErrorMessage.WRONG_ENROLL_CODE))
            return errorModifier(response, ErrorMessage.WRONG_ENROLL_CODE, 400);

        return response;
    }

    public ErrorResponse errorModifier(ErrorResponse response, String message, Integer status) {
        response.setMessage(message);
        response.setStatus(status);

        return response;
    }
}
