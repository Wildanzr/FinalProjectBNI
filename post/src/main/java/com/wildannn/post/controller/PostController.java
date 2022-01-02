package com.wildannn.post.controller;

import com.wildannn.post.entity.Post;
import com.wildannn.post.handler.MessageResponse;
import com.wildannn.post.model.PostModel;
import com.wildannn.post.payload.*;
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
        PostModel model = postService.convertToModel(post1);
        PostWithDetailsResponse response = responseService
                .makePostWithDetailResponse(MessageResponse.CREATE_POST, model);

        return ResponseEntity.status(201).body(response);
    }

    @PostMapping("/{id}/likedby/{userId}")
    public ResponseEntity<?> likePost(@PathVariable("id") Long id, @PathVariable("userId") Long userId) {
        try {
            postStatService.likePost(id, userId);
            LikeResponse response = responseService
                    .makeLikeOrUnlikeResponse(MessageResponse.SUCCESS_LIKE);

            return ResponseEntity.ok().body(response);
        } catch(Exception ex) {
            ErrorResponse error = errorMessageService.errorDefinition(ex);

            return ResponseEntity.status(error.getStatus()).body(error);
        }
    }

    @DeleteMapping("/{id}/unlikedby/{userId}")
    public ResponseEntity<?> unlikePost(@PathVariable("id") Long id, @PathVariable("userId") Long userId) {
        try {
            postStatService.unlikePost(id, userId);
            LikeResponse response = responseService
                    .makeLikeOrUnlikeResponse(MessageResponse.SUCCESS_UNLIKE);

            return ResponseEntity.ok().body(response);
        } catch(Exception ex) {
            ErrorResponse error = errorMessageService.errorDefinition(ex);

            return ResponseEntity.status(error.getStatus()).body(error);
        }
    }

    @GetMapping
    public ResponseEntity<?> listPost() {
        List<Post> posts = postService.findAll();
        List<PostModel> models = postService.convertToModels(posts);
        PostWithDetailsResponse response = responseService
                .makePostWithDetailsResponse(MessageResponse.GET_ALL_POST, models);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findPost(@PathVariable("id") Long id) {
        try {
            Post post = postService.findById(id);
            PostModel model = postService.convertToModel(post);
            PostWithDetailsResponse response = responseService
                    .makePostWithDetailResponse(MessageResponse.GET_POST, model);

            return ResponseEntity.ok().body(response);
        } catch(Exception ex) {
            ErrorResponse error = errorMessageService.errorDefinition(ex);

            return ResponseEntity.status(error.getStatus()).body(error);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updatePost(@PathVariable("id") Long id, @Valid @RequestBody Post post) {
        try {
            Post updated = postService.update(id, post);
            PostModel model = postService.convertToModel(updated);
            PostWithDetailsResponse response = responseService
                    .makePostWithDetailResponse(MessageResponse.UPDATE_POST, model);

            return ResponseEntity.ok().body(response);
        } catch(Exception ex) {
            ErrorResponse error = errorMessageService.errorDefinition(ex);

            return ResponseEntity.status(error.getStatus()).body(error);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable("id") Long id) {
        try {
            postService.delete(id);
            PostWithDetailsResponse response = responseService
                    .makePostWithDetailResponse(MessageResponse.DELETE_POST, null);

            return ResponseEntity.ok().body(response);
        } catch(Exception ex) {
            ErrorResponse error = errorMessageService.errorDefinition(ex);

            return ResponseEntity.status(error.getStatus()).body(error);
        }
    }
}
