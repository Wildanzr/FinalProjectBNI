package com.wildannn.post.handler;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class MessageResponse {

    //200 Ok
    public final static String GET_ALL_POST = "Success get all post";
    public final static String GET_ALL_COMMENT = "Success get all comment";


    public final static String GET_POST = "Success get post";
    public final static String GET_COMMENT = "Success get comment";

    public final static String UPDATE_POST = "Success update post";
    public final static String UPDATE_COMMENT = "Success update comment";

    public final static String DELETE_POST = "Success delete post";
    public final static String DELETE_COMMENT = "Success delete comment";

    //201 Created
    public final static String CREATE_POST = "Success create post";
    public final static String CREATE_COMMENT = "Success create comment";

    //400 Bad request

    //404 Not found
    public final static String COMMENT_NOT_FOUND = "Data komentar tidak ditemukan";
    public final static String POST_NOT_FOUND = "Data post tidak ditemukan";
    public final static String POST_STAT_NOT_FOUND = "Data post tidak ditemukan";
}
