package com.example.btsbackendtest.models;

import jakarta.validation.constraints.Email;
import lombok.*;

public class Request {

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class InitData {
        @NonNull
        private String username;
        @NonNull
        private String email;
        @NonNull
        private String password;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Login {
        @NonNull
        private String username;
        @NonNull
        private String password;
    }

}
