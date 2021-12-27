package com.wildannn.poststat.controller;

import com.wildannn.poststat.model.PostStat;
import com.wildannn.poststat.payload.InternalErr;
import com.wildannn.poststat.service.PostStatService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post-stats")
public class PostStatController {

    @Autowired
    private final PostStatService postStatService;

    @GetMapping
    public ResponseEntity<?> getAllPostStat() {
        List<PostStat> postStatList = postStatService.findAll();
        return ResponseEntity.ok(postStatList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Integer id) {
        try {
            PostStat postStat = postStatService.findById(id);
            return ResponseEntity.ok(postStat);
        } catch(Exception ex) {
            InternalErr error = new InternalErr(ex.getMessage());
            return ResponseEntity.internalServerError().body(error);
        }
    }

    @PostMapping
    public ResponseEntity<?> addPostStats(@RequestBody PostStat postStat) {
        PostStat created = postStatService.addPostStat(postStat);
        return ResponseEntity.ok(created);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updatePostStats(@PathVariable("id") Integer id, @RequestBody PostStat postStat) {
        try {
            PostStat updated = postStatService.update(id, postStat);
            return ResponseEntity.ok(updated);
        } catch(Exception ex) {
            InternalErr error = new InternalErr(ex.getMessage());
            return ResponseEntity.internalServerError().body(error);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePostStats(@PathVariable("id") Integer id) {
        try {
            postStatService.delete(id);
            return ResponseEntity.ok().build();
        } catch(Exception ex) {
            InternalErr error = new InternalErr(ex.getMessage());
            return ResponseEntity.internalServerError().body(error);
        }
    }
}
