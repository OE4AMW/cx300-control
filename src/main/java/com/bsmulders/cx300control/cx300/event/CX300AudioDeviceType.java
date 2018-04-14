package com.bsmulders.cx300control.cx300.event;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toMap;

import java.util.Map;

public enum CX300AudioDeviceType {
    HANDSET("40"),
    SPEAKER("50"),
    HEADSET("60"),
    NO_CHANGE("00"),
    SPEAKER_LOUD("01"),
    SPEAKER_VERY_LOUD("02");

    private static final Map<String, CX300AudioDeviceType> MAP =
            stream(CX300AudioDeviceType.values()).collect(
                    toMap(cx300AudioDeviceType -> cx300AudioDeviceType.message, cx300AudioDeviceType -> cx300AudioDeviceType));

    private final String message;

    CX300AudioDeviceType(String message) {
        this.message = message;
    }

    public static CX300AudioDeviceType of(String message) {
        return MAP.get(message);
    }
}
