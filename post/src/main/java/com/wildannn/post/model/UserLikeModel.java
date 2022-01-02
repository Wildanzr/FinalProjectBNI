package com.wildannn.post.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class UserLikeModel {
    @JsonProperty("post_id")
    private Long postId;

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("created_at")
    private Date createdAt;

    @JsonProperty("updated_at")
    private Date updatedAt;
}
