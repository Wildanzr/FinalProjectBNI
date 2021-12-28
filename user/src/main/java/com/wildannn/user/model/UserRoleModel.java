package com.wildannn.user.model;

import lombok.Data;

import java.util.Date;

@Data
public class UserRoleModel {
    private String id;
    private String name;
    private Date created_at;
    private Date updated_at;
}
