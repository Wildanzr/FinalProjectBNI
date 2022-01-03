package com.wildannn.post.payload;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class LikeResponse {
    private Boolean success = true;
    private String message;

    @Builder
    public LikeResponse(String message) {
        this.message = message;
    }
}
