package com.wildannn.post.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Komentar tidak boleh kosong")
    @Size(min = 1, max = 255, message = "Komentar terdiri dari 1 hingga 255 karakter")
    @Column(columnDefinition = "TEXT")
    private String comment;

    @NotNull(message = "Post ID tidak boleh kosong")
    @Column(name = "post_id")
    @JsonProperty("post_id")
    private Integer postId;

    @NotNull(message = "User ID tidak boleh kosong")
    @Column(name = "user_id")
    @JsonProperty("user_id")
    private Integer userId;

    @CreationTimestamp
    @Column(name = "created_at")
    @JsonProperty("created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    @JsonProperty("updated_at")
    private Date updatedAt;

    @Builder
    public Comment(Long id, String comment, Integer postId, Integer userId) {
        this.id = id;
        this.comment = comment;
        this.postId = postId;
        this.userId = userId;
    }
}
