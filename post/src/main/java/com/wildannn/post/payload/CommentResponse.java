package com.wildannn.post.payload;

import com.wildannn.post.entity.Comment;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CommentResponse {

    private Boolean status = true;
    private String message;
    private List<Comment> data;

    @Builder
    public CommentResponse(String message, List<Comment> data) {
        this.message = message;
        this.data = data;
    }
}
