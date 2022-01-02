package com.wildannn.post.model;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class PostWithCommentModel {
    private Long id;
    private String content;
    private Long userId;
    private Integer trainingTopicId;
    private Date createdAt;
    private Date updatedAt;
    private List<CommentModel> comments;
}
