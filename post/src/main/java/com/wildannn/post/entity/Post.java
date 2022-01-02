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
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Content tidak boleh kosong")
    @Size(min = 1, max = 1000, message = "Konten terdiri dari 1 hingga 1000 karakter")
    @Column(columnDefinition = "TEXT")
    private String content;

    @NotNull(message = "User ID tidak boleh kosong")
    @Column(name = "user_id")
    @JsonProperty("user_id")
    private Long userId;

    @NotNull(message = "Trainig topic ID tidak boleh kosong")
    @Column(name = "training_topic_id")
    @JsonProperty("training_topic_id")
    private Integer trainingTopicId;

    @Column(name = "created_at")
    @JsonProperty("created_at")
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at")
    @JsonProperty("updated_at")
    @UpdateTimestamp
    private Date updatedAt;

    @Builder
    public Post(String content, Long userId, Integer trainingTopicId) {
        this.content = content;
        this.userId = userId;
        this.trainingTopicId = trainingTopicId;
    }
}
