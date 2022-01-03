package com.wildannn.post.controller;

import com.wildannn.post.entity.Comment;
import com.wildannn.post.handler.MessageResponse;
import com.wildannn.post.payload.CommentResponse;
import com.wildannn.post.payload.ErrorResponse;
import com.wildannn.post.payload.ResponseService;
import com.wildannn.post.service.CommentService;
import com.wildannn.post.service.impl.ErrorMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final ResponseService responseService;
    private final ErrorMessageService errorMessageService;

    @PostMapping("/{postId}/commentby/{userId}")
    public ResponseEntity<?> create(@PathVariable("postId") Integer postId, @PathVariable("userId") Integer userId,
                                    @Valid @RequestBody Comment req) {
        try {
            Comment model = commentService.create(req, postId, userId);
            CommentResponse response = responseService
                    .makeCommentResponse(MessageResponse.CREATE_COMMENT, model);

            return ResponseEntity.status(201).body(response);
        } catch(Exception ex) {
            ErrorResponse error = errorMessageService.errorDefinition(ex);

            return ResponseEntity.status(error.getStatus()).body(error);
        }
    }

    @DeleteMapping("/{postId}/commentby/{userId}/at/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable("postId") Integer postId,
                                           @PathVariable("userId") Integer userId,
                                           @PathVariable("id") Long id) {
        try {
            commentService.delete(postId, userId, id);
            CommentResponse response = responseService
                    .makeCommentResponse(MessageResponse.DELETE_COMMENT, null);

            return ResponseEntity.ok().body(response);
        } catch(Exception ex) {
            ErrorResponse error = errorMessageService.errorDefinition(ex);

            return ResponseEntity.status(error.getStatus()).body(error);
        }
    }
}