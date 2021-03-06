package com.wildannn.post.service.impl;

import com.wildannn.post.handler.MessageResponse;
import com.wildannn.post.payload.ErrorResponse;
import org.springframework.stereotype.Service;

@Service
public class ErrorMessageService {

    public ErrorResponse errorDefinition(Exception ex) {
        ErrorResponse response = ErrorResponse.builder()
                .message(ex.getMessage())
                .status(400)
                .build();

        //Todo add handler dictionary
        if(ex.getMessage().equals(MessageResponse.COMMENT_NOT_FOUND))
            response = errorModifier(response, MessageResponse.COMMENT_NOT_FOUND, 404);

        else if(ex.getMessage().equals(MessageResponse.POST_NOT_FOUND))
            response = errorModifier(response, MessageResponse.POST_NOT_FOUND, 404);

        else if(ex.getMessage().equals(MessageResponse.POST_STAT_NOT_FOUND))
            response = errorModifier(response, MessageResponse.POST_STAT_NOT_FOUND, 404);

        else if(ex.getMessage().equals(MessageResponse.HAVE_LIKED))
            response = errorModifier(response, MessageResponse.HAVE_LIKED, 400);

        else if(ex.getMessage().equals(MessageResponse.POST_NOT_FOUND))
            response = errorModifier(response, MessageResponse.POST_NOT_FOUND, 404);

        return response;
    }

    public ErrorResponse errorModifier(ErrorResponse response, String message, Integer status) {
        response.setMessage(message);
        response.setStatus(status);

        return response;
    }
}
