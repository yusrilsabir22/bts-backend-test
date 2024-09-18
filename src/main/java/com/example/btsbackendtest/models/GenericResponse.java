package com.example.btsbackendtest.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class GenericResponse<T> {
    private boolean success;
    private String message;
    private int status;
    private T data;

    public static <T> GenericResponse<T> empty() {
        return success(null);
    }

    public static <T> GenericResponse<T> success(T data) {
        return GenericResponse.<T>builder()
                .message("SUCCESS!")
                .data(data)
                .status(200)
                .success(true)
                .build();
    }

    public static <T> GenericResponse<T> error(int statusCode) {
        return GenericResponse.<T>builder()
                .message("ERROR!")
                .status(statusCode)
                .success(false)
                .build();
    }
}
