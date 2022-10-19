package com.example.base_ij.members.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor

public class ResponsDto<T> {

    private boolean success;
    private T data;
    private Error error;

    public static <T> ResponsDto<T> success(T data) {
        return new ResponsDto<>(true, data, null);
    }

    public static <T> ResponsDto<T> fail(String code, String message) {
        return new ResponsDto<>(false, null, new Error(code, message));
    }

    @Getter
    @Setter
    @AllArgsConstructor
    static class Error {
        private String code;
        private String message;
    }
}
