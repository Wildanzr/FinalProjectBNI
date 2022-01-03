package com.wildannn.post.model;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class PostModel {
    private Long id;
    private String content;
    private Long userId;
    private Integer trainingTopicId;
    private List<StatModel> stats;
    private Date createdAt;
    private Date updatedAt;
}
