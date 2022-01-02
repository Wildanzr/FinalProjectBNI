package com.wildannn.post.handler;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class MessageResponse {

    //200 Ok
    public final static String GET_ALL_POST = "Success get all post";
    public final static String GET_POST = "Success get post";

    public final static String UPDATE_POST = "Success update post";

    public final static String DELETE_POST = "Success delete post";

    //201 Created
    public final static String CREATE_POST = "Success create post";

    //400 Bad request

    //404 Not found
    public final static String COMMENT_NOT_FOUND = "Data komentar tidak ditemukan";
}
