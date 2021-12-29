package com.wildannn.user.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

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

    @JsonProperty("training_topic_id")
    private Integer trainingTopic;

    private Date createdAt;
    private Date updatedAt;
}
