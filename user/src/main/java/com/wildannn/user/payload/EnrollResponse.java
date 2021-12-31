package com.wildannn.user.payload;

import com.wildannn.user.model.EnrollModel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class EnrollResponse {
    private Boolean success = true;
    private String message;
    private Date timestamp = new Date();
    private List<EnrollModel> data;

    @Builder
    public EnrollResponse(String message, List<EnrollModel> data) {
        this.message = message;
        this.data = data;
    }
}
