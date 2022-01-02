package com.wildannn.post.controller;

import com.wildannn.post.entity.Post;
import com.wildannn.post.entity.PostStat;
import com.wildannn.post.handler.MessageResponse;
import com.wildannn.post.payload.ErrorResponse;
import com.wildannn.post.payload.PostResponse;
import com.wildannn.post.payload.ResponseService;
import com.wildannn.post.service.PostService;
import com.wildannn.post.service.PostStatService;
import com.wildannn.post.service.impl.ErrorMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
@Log4j2
public class PostController {

    private final PostService postService;
    private final PostStatService postStatService;
    private final ErrorMessageService errorMessageService;
    private final ResponseService responseService;

    @PostMapping
    public ResponseEntity<?> createPost(@Valid @RequestBody Post post) {
        Post post1 = postService.addPost(post);
        postStatService.make();
        PostResponse response = responseService
                .makePostResponse(MessageResponse.CREATE_POST, post1);

        return ResponseEntity.status(201).body(response);
    }

    @GetMapping
    public ResponseEntity<?> listPost() {
        List<Post> posts = postService.findAll();
        PostResponse response = responseService
                .makePostsResponse(MessageResponse.GET_ALL_POST, posts);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findPost(@PathVariable("id") Long id) {
        try {
            Post post = postService.findById(id);
            PostResponse response = responseService
                    .makePostResponse(MessageResponse.GET_POST, post);

            return ResponseEntity.ok(response);
        } catch(Exception ex) {
            ErrorResponse error = errorMessageService.errorDefinition(ex);

            return ResponseEntity.status(error.getStatus()).body(error);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updatePost(@PathVariable("id") Long id, @Valid @RequestBody Post post) {
        try {
            Post updated = postService.update(id, post);
            PostResponse response = responseService
                    .makePostResponse(MessageResponse.UPDATE_POST, updated);

            return ResponseEntity.ok(response);
        } catch(Exception ex) {
            ErrorResponse error = errorMessageService.errorDefinition(ex);

            return ResponseEntity.status(error.getStatus()).body(error);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable("id") Long id) {
        try {
            postService.delete(id);
            PostResponse response = responseService
                    .makePostResponse(MessageResponse.DELETE_POST, null);

            return ResponseEntity.ok().body(response);
        } catch(Exception ex) {
            ErrorResponse error = errorMessageService.errorDefinition(ex);

            return ResponseEntity.status(error.getStatus()).body(error);
        }
    }
}
