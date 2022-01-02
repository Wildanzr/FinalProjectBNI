package com.wildannn.post.handler;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class MessageResponse {

    //200 Ok

    //201 Created
    public final static String CREATE_POST = "Success create post";

    //400 Bad request

    //404 Not found
    public final static String COMMENT_NOT_FOUND = "Data komentar tidak ditemukan";
}
