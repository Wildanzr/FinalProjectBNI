package com.wildannn.post.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bouncycastle.jcajce.provider.symmetric.IDEA;

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

    @Column(name = "user_likes_id")
    @JsonProperty("user_likes_id")
    private Long userLikedId;

    @Column(name = "created_at")
    @JsonProperty("created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    @JsonProperty("updated_at")
    private Date updatedAt;

    public PostStat(Integer likes, Integer comments, Integer shares, Long userLikedId) {
        this.likes = likes;
        this.comments = comments;
        this.shares = shares;
        this.userLikedId = userLikedId;

        Date now = new Date();
        this.createdAt = now;
        this.updatedAt = now;
    }
}
