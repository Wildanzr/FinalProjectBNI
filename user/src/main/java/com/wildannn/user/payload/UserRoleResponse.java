package com.wildannn.user.payload;

import com.wildannn.user.model.UserRoleModel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class UserRoleResponse {
    private Boolean status = true;
    private String message;
    private List<UserRoleModel> data;

    @Builder
    public UserRoleResponse(String message, List<UserRoleModel> data) {
        this.message = message;
        this.data = data;
    }
}
