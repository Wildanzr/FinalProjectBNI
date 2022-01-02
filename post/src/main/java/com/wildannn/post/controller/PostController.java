package com.wildannn.post.controller;

import com.wildannn.post.entity.Post;
import com.wildannn.post.handler.MessageResponse;
import com.wildannn.post.payload.ErrorResponse;
import com.wildannn.post.payload.PostResponse;
import com.wildannn.post.payload.ResponseService;
import com.wildannn.post.service.PostService;
import com.wildannn.post.service.impl.ErrorMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final ErrorMessageService errorMessageService;
    private final ResponseService responseService;

    @PostMapping
    public ResponseEntity<?> createPost(@RequestBody Post post) {
        Post post1 = postService.addPost(post);
        PostResponse response = responseService
                .makePostResponse(MessageResponse.CREATE_POST, post1);

        return ResponseEntity.status(201).body(response);
    }

    @GetMapping
    public ResponseEntity<?> listPost() {
        List<Post> postList = postService.findAll();
        return ResponseEntity.ok(postList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findPost(@PathVariable("id") Long id) {
        try {
            Post post = postService.findById(id);
            return ResponseEntity.ok(post);
        } catch(Exception ex) {
            ErrorResponse error = errorMessageService.errorDefinition(ex);

            return ResponseEntity.status(error.getStatus()).body(error);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updatePost(@PathVariable("id") Long id, @RequestBody Post post) {
        try {
            Post updated = postService.update(id, post);
            return ResponseEntity.ok(updated);
        } catch(Exception ex) {
            ErrorResponse error = errorMessageService.errorDefinition(ex);

            return ResponseEntity.status(error.getStatus()).body(error);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable("id") Long id) {
        try {
            postService.delete(id);
            return ResponseEntity.ok().build();
        } catch(Exception ex) {
            ErrorResponse error = errorMessageService.errorDefinition(ex);

            return ResponseEntity.status(error.getStatus()).body(error);
        }
    }
}
