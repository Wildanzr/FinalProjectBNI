package com.wildannn.user.payload;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TokenResponse {
    private String token;

    @Builder
    public TokenResponse(String token) {
        this.token = token;
    }
}
