package com.example.btsbackendtest.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

    USER_UPDATE("user:update"),
    USER_CREATE("user:create"),
    USER_READ("user:read"),

    USER_REMOVE("user:remove")
    ;

    @Getter
    private final String permission;

}
