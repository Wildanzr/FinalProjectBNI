package com.wildannn.post.payload;

import com.wildannn.post.entity.Post;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PostResponse {

    private Boolean success = true;
    private String message;
    private List<Post> data;

    @Builder
    public PostResponse(String message, List<Post> data) {
        this.message = message;
        this.data = data;
    }
}
