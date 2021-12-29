package com.wildannn.user.payload;

import com.wildannn.user.model.UserModel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class UserResponse {
    private Boolean success = true;
    private String message;
    private Date timestamp = new Date();
    private List<UserModel> data;

    @Builder
    public UserResponse(String message, List<UserModel> data) {
        this.message = message;
        this.data = data;
    }
}
