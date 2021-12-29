package com.wildannn.user.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class UserModel {
    private String id;
    private String email;
    private String username;
    private String first_name;
    private String last_name;
    private String gender;
    private Integer status;
    private UserRoleModel user_role;
    private Integer training_topic_id;
    private Date created_at;
    private Date updated_at;
}
