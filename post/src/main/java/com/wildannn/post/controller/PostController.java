package com.wildannn.post.controller;

import com.wildannn.post.entity.Post;
import com.wildannn.post.payload.InternalErr;
import com.wildannn.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<?> createPost(@RequestBody Post post) {
        Post created = postService.addPost(post);
        return ResponseEntity.ok(created);
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
            InternalErr error = new InternalErr(ex.getMessage());
            return ResponseEntity.internalServerError().body(error);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updatePost(@PathVariable("id") Long id, @RequestBody Post post) {
        try {
            Post updated = postService.update(id, post);
            return ResponseEntity.ok(updated);
        } catch (Exception ex) {
            InternalErr error = new InternalErr(ex.getMessage());
            return ResponseEntity.internalServerError().body(error);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable("id") Long id) {
        try {
            postService.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception ex) {
            InternalErr error = new InternalErr(ex.getMessage());
            return ResponseEntity.internalServerError().body(error);
        }
    }
}
