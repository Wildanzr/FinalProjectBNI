package com.wildannn.post.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "user_likes")
public class UserLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "post_id")
    @JsonProperty("post_id")
    private Long postId;

    @Column(name = "user_id")
    @JsonProperty("user_id")
    private Long userId;

    @Column(name = "created_at")
    @JsonProperty("created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    @JsonProperty("updated_at")
    private Date updatedAt;
}
