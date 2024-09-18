package com.example.btsbackendtest.auth;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponse<T> {
    private int status;
    private String message;
    private String accessToken;

//    @Data
//    @Builder
//    public static class Hasil {
//        private String token;
//        private Info info;
//    }
//
//    @Data
//    @Builder
//    public static class Info {
//        private String username;
//        private String email;
//    }
}
