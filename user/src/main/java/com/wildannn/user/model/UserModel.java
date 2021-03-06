package com.wildannn.user.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class UserModel {
    private String id;
    private String email;
    private String username;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    private String gender;
    private Integer status;

    @JsonProperty("user_role")
    private UserRoleModel userRoleModel;

    @JsonProperty("training_topics")
    private List<TrainingTopicModel> topics;

    @JsonProperty("created_at")
    private Date createdAt;

    @JsonProperty("updated_at")
    private Date updatedAt;
}
