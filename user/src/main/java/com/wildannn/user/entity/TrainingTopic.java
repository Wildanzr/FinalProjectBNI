package com.wildannn.user.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@Document("training_topics")
@Data
public class TrainingTopic {

    @Transient
    public static final String SEQUENCE = "training_topic_db_sequence";

    @Id
    private String id;

    @NotEmpty(message = "Nama training topic tidak boleh kosong")
    @Size(min = 3, max = 100, message = "Nama training topic terdiri dari 3 hingga 100 karakter")
    private String name;

    @NotEmpty(message = "EnrollCode tidak boleh kosong")
    @Size(min = 5, max = 10, message = "EnrollCode terdiri dari 5 hingga 10 karakter")
    @JsonProperty("enroll_code")
    private String enrollCode;

    @NotEmpty
    @Size(min = 3, max = 1000, message = "Deskripsi terdiri dari 3 hingga 1000 karakter")
    private String description;

    @JsonProperty("created_at")
    private Date createdAt;

    @JsonProperty("updated_at")
    private Date updatedAt;

    @Builder
    public TrainingTopic(String id, String name, String enrollCode, String description) {
        this.id = id;
        this.name = name;
        this.enrollCode = enrollCode;
        this.description = description;

        this.createdAt = new Date();
        this.updatedAt = new Date();
    }
}
