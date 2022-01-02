package com.wildannn.post.payload;

import com.wildannn.post.entity.Comment;
import com.wildannn.post.entity.Post;
import com.wildannn.post.entity.PostStat;
import com.wildannn.post.model.PostModel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@NoArgsConstructor
public class ResponseService {

    public PostResponse makePostResponse(String message, Post post) {
        List<Post> model = new ArrayList<>();
        model.add(post);

        return PostResponse.builder()
                .message(message)
                .data(model)
                .build();
    }

    public PostResponse makePostsResponse(String message, List<Post> posts) {
        return PostResponse.builder()
                .message(message)
                .data(posts)
                .build();
    }

    public CommentResponse makeCommentResponse(String message, Comment comment) {
        List<Comment> model = new ArrayList<>();
        model.add(comment);

        return CommentResponse.builder()
                .message(message)
                .data(model)
                .build();
    }

    public CommentResponse makeCommentsResponse(String message, List<Comment> comments) {
        return CommentResponse.builder()
                .message(message)
                .data(comments)
                .build();
    }

    public StatResponse makeStatResponse(String message, PostStat stat) {
        List<PostStat> model = new ArrayList<>();
        model.add(stat);

        return StatResponse.builder()
                .message(message)
                .data(model)
                .build();
    }

    public PostWithDetailsResponse makePostWithDetailResponse(String message, PostModel model) {
        List<PostModel> models = new ArrayList<>();
        models.add(model);

        return PostWithDetailsResponse.builder()
                .message(message)
                .data(models)
                .build();
    }

    public PostWithDetailsResponse makePostWithDetailsResponse(String message, List<PostModel> models) {
        return PostWithDetailsResponse.builder()
                .message(message)
                .data(models)
                .build();
    }

    public LikeResponse makeLikeOrUnlikeResponse(String message) {
        return LikeResponse.builder()
                .message(message)
                .build();
    }
}
