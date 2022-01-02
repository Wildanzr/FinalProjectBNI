package com.wildannn.user.payload;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TokenResponse {
    private Boolean status = true;
    private String message;
    private String token;

    @Builder
    public TokenResponse(String message, String token) {
        this.message = message;
        this.token = token;
    }
}
