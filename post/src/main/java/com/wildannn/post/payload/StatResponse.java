package com.wildannn.post.payload;

import com.wildannn.post.entity.PostStat;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class StatResponse {
    private Boolean success = true;
    private String message;
    private List<PostStat> data;

    @Builder
    public StatResponse(String message, List<PostStat> data) {
        this.message = message;
        this.data = data;
    }
}
