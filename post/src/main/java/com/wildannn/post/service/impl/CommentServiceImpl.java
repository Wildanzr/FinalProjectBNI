package com.wildannn.post.service.impl;

import com.wildannn.post.entity.Comment;
import com.wildannn.post.entity.Post;
import com.wildannn.post.handler.MessageResponse;
import com.wildannn.post.repository.CommentRepository;
import com.wildannn.post.service.CommentService;
import com.wildannn.post.service.PostService;
import com.wildannn.post.service.PostStatService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    @Autowired
    private final CommentRepository commentRepository;

    @Autowired
    private final PostService postService;

    @Autowired
    private final PostStatService statService;

    @Override
    public Comment create(Comment comment, Integer postId, Integer userId) {
        Long longPostId = postId.longValue();
        Long longUserId = userId.longValue();
        Post post = postService.findById(longPostId);

        if(post == null)
            throw new RuntimeException(MessageResponse.POST_NOT_FOUND);

        comment.setPostId(postId);
        comment.setUserId(userId);
        statService.comment(longPostId, longUserId);

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
    public void delete(Integer postId, Integer userId, Long id) {
        Long longPostId = postId.longValue();
        Long longUserId = userId.longValue();
        Comment deleted = commentRepository.findByPostIdAndUserIdAndId(postId, userId, id);
        statService.uncomment(longPostId, longUserId);

        commentRepository.delete(deleted);
    }
}
