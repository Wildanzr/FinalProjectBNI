package com.wildannn.post.payload;

import com.wildannn.post.model.PostWithCommentModel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PostWithDetails {
    private Boolean success = true;
    private String message;
    private List<PostWithCommentModel> data;

    @Builder
    public PostWithDetails(String message, List<PostWithCommentModel> data) {
        this.message = message;
        this.data = data;
    }
}
