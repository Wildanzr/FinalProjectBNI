package com.wildannn.comment.controller;

import com.wildannn.comment.model.Comment;
import com.wildannn.comment.payload.InternalErr;
import com.wildannn.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<?> addComment(@RequestBody Comment comment) {
        Comment newComment = commentService.addComment(comment);
        return ResponseEntity.ok(newComment);
    }

    @GetMapping
    public ResponseEntity<?> getAllComment() {
        List<Comment> commentList = commentService.findAll();
        return ResponseEntity.ok(commentList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findComment(@PathVariable("id") Integer id) {
        try {
            Comment comment = commentService.findById(id);
            return ResponseEntity.ok(comment);
        } catch(Exception ex) {
            InternalErr error = new InternalErr(ex.getMessage());
            return ResponseEntity.internalServerError().body(error);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateComment(@PathVariable("id") Integer id, @RequestBody Comment update) {
        try {
            Comment updated = commentService.update(id, update);
            return ResponseEntity.ok(updated);
        } catch(Exception ex) {
            InternalErr error = new InternalErr(ex.getMessage());
            return ResponseEntity.internalServerError().body(error);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable("id") Integer id) {
        try {
            commentService.delete(id);
            return ResponseEntity.ok().build();
        } catch(Exception ex) {
            InternalErr error = new InternalErr(ex.getMessage());
            return ResponseEntity.internalServerError().body(error);
        }
    }
}
