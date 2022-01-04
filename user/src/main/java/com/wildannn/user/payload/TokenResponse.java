package com.wildannn.user.payload;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TokenResponse {
    private Boolean status = true;
    private String message;
    private String data;

    @Builder
    public TokenResponse(String message, String data) {
        this.message = message;
        this.data = data;
    }
}
