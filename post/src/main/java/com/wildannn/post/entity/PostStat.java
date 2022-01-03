package com.wildannn.post.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "post_stats")
public class PostStat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    private Integer likes;
    private Integer comments;
    private Integer shares;

    @Column(name = "created_at")
    @JsonProperty("created_at")
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at")
    @JsonProperty("updated_at")
    @UpdateTimestamp
    private Date updatedAt;

    @Builder
    public PostStat(Integer likes, Integer comments, Integer shares) {
        this.likes = likes;
        this.comments = comments;
        this.shares = shares;
    }
}
