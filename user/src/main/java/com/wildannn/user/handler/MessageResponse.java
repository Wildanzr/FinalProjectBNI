package com.wildannn.user.handler;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class MessageResponse {

    //200 Ok
    public final static String SUCCESS_APPROVE = "Success approve user";
    public final static String SUCCESS_APPROVE_ALL = "Success approve all user";
    public final static String SUCESS_ENROLL = "Success enroll training topic";
    public final static String SUCESS_UNENROLL = "Success unenroll training topic";

    public final static String ALL_USER = "Success get all user";
    public final static String ALL_UNAPPROVED_USER = "Success get all unapproved user";
    public final static String ALL_TOPIC = "Success get training aLL topic";
    public final static String ALL_ROLE = "Success Success get all role";

    public final static String USER = "Success get user";
    public final static String ONE_TOPIC = "Success get training topic";
    public final static String ONE_ROLE = "Success Success get role";

    public final static String UPDATED_USER = "Success updated user";
    public final static String UPDATED_TOPIC = "Success updated training topic";
    public final static String UPDATED_ROLE = "Success update role";

    public final static String DELETED_USER = "Success deleted user";
    public final static String DELETED_TOPIC = "Success deleted training topic";
    public final static String DELETED_ROLE = "Success delete role";

    public final static String LOGIN_SUCCESS = "Success login";

    //201 Created
    public final static String CREATED_USER = "Success created user";
    public final static String CREATED_TOPIC = "Success create training topic";
    public final static String CREATED_ROLE = "Success create role";

    //404 Not Found
    public final static String NOT_FOUND = "Data tidak ditemukan";
    public final static String NOT_ENROLLED = "Tidak ada data enroll ditemukan";
    public final static String AN_ID_NOT_FOUND = "Salah satu userId tidak ditemukan";

    //400 Bad Request
    public final static String EMAIL_REGISTERED = "Alamat email telah didaftarkan";
    public final static String USERNAME_REGISTERED = "Nama user telah digunakan";
    public final static String WRONG_ENROLL_CODE = "Kode enroll tidak sesuai";
    public final static String HAVE_ENROLLED = "Tidak bisa enroll pada training topic yang telah diambil";
    public final static String EMPTY_IDS = "Data user id tidak boleh kosong";
}
