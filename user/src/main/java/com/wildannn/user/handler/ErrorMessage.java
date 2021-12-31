package com.wildannn.user.handler;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ErrorMessage {
    public final static String NOT_FOUND = "Data tidak ditemukan";
    public final static String EMAIL_REGISTERED = "Alamat email telah didaftarkan";
    public final static String USERNAME_REGISTERED = "Nama user telah digunakan";
    public final static String WRONG_ENROLL_CODE = "Kode enroll tidak sesuai";
    public final static String HAVE_ENROLLED = "Tidak bisa enroll pada training topic yang telah diambil";
    public final static String NOT_ENROLLED = "Tidak ada data enroll ditemukan";
    public final static String AN_ID_NOT_FOUND = "Salah satu userId tidak ditemukan";
    public final static String EMPTY_IDS = "Data user id tidak boleh kosong";
}
