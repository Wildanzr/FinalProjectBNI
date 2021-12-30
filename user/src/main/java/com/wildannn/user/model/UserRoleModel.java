package com.wildannn.user.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class UserRoleModel {
    private String id;
    private String name;

    @JsonProperty("created_at")
    private Date createdAt;
}
