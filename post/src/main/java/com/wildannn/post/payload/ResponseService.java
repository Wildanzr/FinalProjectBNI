package com.wildannn.post.payload;

import com.wildannn.post.entity.Post;
import com.wildannn.post.payload.PostResponse;
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
}
