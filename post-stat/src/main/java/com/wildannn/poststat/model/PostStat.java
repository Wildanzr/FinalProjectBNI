package com.wildannn.poststat.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="post_stats")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostStat {

    @Id
    private Integer post_id;

    private Integer likes;
    private Integer shares;
    private Integer comments;

    @ElementCollection
    private List<Integer> user_likes = new ArrayList<>();

    private Integer user_last_like_id;
    private Date created_at;
    private Date updated_at;
}
