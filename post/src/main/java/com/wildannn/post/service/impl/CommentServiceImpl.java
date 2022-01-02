package com.wildannn.post.service.impl;

import com.wildannn.post.entity.Comment;
import com.wildannn.post.handler.MessageResponse;
import com.wildannn.post.repository.CommentRepository;
import com.wildannn.post.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Override
    public Comment create(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public Comment findById(Long id) {
        return commentRepository.findById(id).orElseThrow(() -> {
            throw new RuntimeException(MessageResponse.COMMENT_NOT_FOUND);
        });
    }

    @Override
    public Comment update(Long id, Comment comment) {
        Comment update = this.findById(id);
        update.setComment(comment.getComment());
        update.setUpdatedAt(comment.getUpdatedAt());

        return commentRepository.save(update);
    }

    @Override
    public void delete(Long id) {
        Comment deleted = this.findById(id);

        commentRepository.delete(deleted);
    }
}
