package com.wildannn.user.model;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class UserRoleModel {
    private String id;
    private String name;
}
