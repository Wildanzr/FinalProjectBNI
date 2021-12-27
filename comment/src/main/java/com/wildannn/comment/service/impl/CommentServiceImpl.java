package com.wildannn.comment.service.impl;

import com.wildannn.comment.model.Comment;
import com.wildannn.comment.repository.CommentRepository;
import com.wildannn.comment.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Comment addComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public Comment findById(Integer id) {
        return commentRepository.findById(id).orElseThrow(()->{
            throw new RuntimeException("Not Found!");
        });
    }

    @Override
    public Comment update(Integer id, Comment updated) {
        Comment updateComment = this.findById(id);
        updateComment.setUpdated_at(updated.getUpdated_at());
        updateComment.setComment(updated.getComment());

        return commentRepository.save(updateComment);
    }

    @Override
    public void delete(Integer id) {
        Comment deleted = this.findById(id);
        commentRepository.delete(deleted);
    }
}
