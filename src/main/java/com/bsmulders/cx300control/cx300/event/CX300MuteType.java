package com.bsmulders.cx300control.cx300.event;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toMap;

import java.util.Map;

public enum CX300MuteType {
    NOT_MUTED("00"),
    MUTED("01");

    private static final Map<String, CX300MuteType> MAP =
            stream(CX300MuteType.values()).collect(toMap(cx300MuteType -> cx300MuteType.message, cx300MuteType -> cx300MuteType));

    private final String message;

    CX300MuteType(String message) {
        this.message = message;
    }

    public static CX300MuteType of(String message) {
        return MAP.get(message);
    }
}
