package com.wildannn.user.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class UserRoleModel {
    private String id;
    private String name;
    private Date since;
}
