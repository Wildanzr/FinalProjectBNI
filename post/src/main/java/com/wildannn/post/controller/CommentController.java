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
import java.util.List;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final ResponseService responseService;
    private final ErrorMessageService errorMessageService;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Comment req) {
        Comment model = commentService.create(req);
        CommentResponse response = responseService
                .makeCommentResponse(MessageResponse.CREATE_COMMENT, model);


        return ResponseEntity.status(201).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findComment(@PathVariable("id") Long id) {
        try {
            Comment model = commentService.findById(id);
            CommentResponse response = responseService
                    .makeCommentResponse(MessageResponse.GET_COMMENT, model);

            return ResponseEntity.ok().body(response);
        } catch(Exception ex) {
            ErrorResponse error = errorMessageService.errorDefinition(ex);

            return ResponseEntity.status(error.getStatus()).body(error);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllComment() {
        List<Comment> models = commentService.findAll();
        CommentResponse response = responseService
                .makeCommentsResponse(MessageResponse.GET_ALL_COMMENT, models);

        return ResponseEntity.ok().body(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateComment(@PathVariable("id") Long id, @Valid @RequestBody Comment comment) {
        try {
            Comment model = commentService.update(id, comment);
            CommentResponse response = responseService
                    .makeCommentResponse(MessageResponse.UPDATE_COMMENT, model);

            return ResponseEntity.ok().body(response);
        } catch(Exception ex) {
            ErrorResponse error = errorMessageService.errorDefinition(ex);

            return ResponseEntity.status(error.getStatus()).body(error);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable("id") Long id) {
        try {
            commentService.delete(id);
            CommentResponse response = responseService
                    .makeCommentResponse(MessageResponse.DELETE_COMMENT, null);

            return ResponseEntity.ok().body(response);
        } catch(Exception ex) {
            ErrorResponse error = errorMessageService.errorDefinition(ex);

            return ResponseEntity.status(error.getStatus()).body(error);
        }
    }
}