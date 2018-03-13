package io.arukas.tools;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Dump {

    private int code;

    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data;

    public static Dump success() {
        Dump dump = Dump.builder().code(200).message("execute successfully").data(null).build();
        return dump;
    }

    public static Dump success(Object data) {
        Dump dump = Dump.builder().code(200).message("execute successfully").data(data).build();
        return dump;
    }

    public static Dump success(String message) {
        Dump dump = Dump.builder().code(200).message(message).data(null).build();
        return dump;
    }

    public static Dump success(String message, Object data) {
        Dump dump = Dump.builder().code(200).message(message).data(data).build();
        return dump;
    }

    public static Dump success(int code, String message) {
        Dump dump = Dump.builder().code(code).message(message).data(null).build();
        return dump;
    }

    public static Dump success(int code, String message, Object data) {
        Dump dump = Dump.builder().code(code).message(message).data(data).build();
        return dump;
    }

    public static Dump fail() {
        Dump dump = Dump.builder().code(500).message("execute failed").data(null).build();
        return dump;
    }

    public static Dump fail(Object data) {
        Dump dump = Dump.builder().code(500).message("execute failed").data(data).build();
        return dump;
    }

    public static Dump fail(String message) {
        Dump dump = Dump.builder().code(500).message(message).data(null).build();
        return dump;
    }

    public static Dump fail(String message, Object data) {
        Dump dump = Dump.builder().code(500).message(message).data(data).build();
        return dump;
    }

    public static Dump fail(int code, String message) {
        Dump dump = Dump.builder().code(code).message(message).data(null).build();
        return dump;
    }

    public static Dump fail(int code, String message, Object data) {
        Dump dump = Dump.builder().code(code).message(message).data(data).build();
        return dump;
    }

}
