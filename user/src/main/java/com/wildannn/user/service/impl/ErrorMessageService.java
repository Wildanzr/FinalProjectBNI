package com.wildannn.user.service.impl;

import com.wildannn.user.handler.MessageResponse;
import com.wildannn.user.payload.ErrorResponse;
import org.springframework.stereotype.Service;

@Service
public class ErrorMessageService {

    public ErrorResponse errorDefinition(Exception ex) {
        ErrorResponse response = ErrorResponse.builder()
                .message("Bad Request")
                .status(400)
                .build();

        if(ex.getMessage().equalsIgnoreCase(MessageResponse.NOT_FOUND))
            return errorModifier(response, MessageResponse.NOT_FOUND, 404);

        else if(ex.getMessage().equalsIgnoreCase(MessageResponse.EMAIL_REGISTERED))
            return errorModifier(response, MessageResponse.EMAIL_REGISTERED, 400);

        else if(ex.getMessage().equalsIgnoreCase(MessageResponse.USERNAME_REGISTERED))
            return errorModifier(response, MessageResponse.USERNAME_REGISTERED, 400);

        else if(ex.getMessage().equalsIgnoreCase(MessageResponse.WRONG_ENROLL_CODE))
            return errorModifier(response, MessageResponse.WRONG_ENROLL_CODE, 400);

        else if(ex.getMessage().equalsIgnoreCase(MessageResponse.HAVE_ENROLLED))
            return errorModifier(response, MessageResponse.HAVE_ENROLLED, 400);

        else if(ex.getMessage().equalsIgnoreCase(MessageResponse.NOT_ENROLLED))
            return errorModifier(response, MessageResponse.NOT_ENROLLED, 400);

        else if(ex.getMessage().equalsIgnoreCase(MessageResponse.AN_ID_NOT_FOUND))
            return errorModifier(response, MessageResponse.AN_ID_NOT_FOUND, 404);

        else if(ex.getMessage().equalsIgnoreCase(MessageResponse.EMPTY_IDS))
            return errorModifier(response, MessageResponse.EMPTY_IDS, 400);

        return response;
    }

    public ErrorResponse errorModifier(ErrorResponse response, String message, Integer status) {
        response.setMessage(message);
        response.setStatus(status);

        return response;
    }
}
