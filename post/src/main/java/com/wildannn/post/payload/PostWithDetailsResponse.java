package com.wildannn.post.payload;

import com.wildannn.post.model.PostModel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PostWithDetailsResponse {
    private Boolean success = true;
    private String message;
    private List<PostModel> data;

    @Builder
    public PostWithDetailsResponse(String message, List<PostModel> data) {
        this.message = message;
        this.data = data;
    }
}
