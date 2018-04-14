package com.bsmulders.cx300control.cx300.event;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toMap;

import java.util.Map;

public enum CX300AudioInputType {
    ENABLED("03"),
    DISABLED("00");

    private static final Map<String, CX300AudioInputType> MAP =
            stream(CX300AudioInputType.values()).collect(
                    toMap(cx300AudioInputType -> cx300AudioInputType.message, cx300AudioInputType -> cx300AudioInputType));

    private final String message;

    CX300AudioInputType(String message) {
        this.message = message;
    }

    public static CX300AudioInputType of(String message) {
        return MAP.get(message);
    }
}
