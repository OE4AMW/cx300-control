package com.bsmulders.cx300control.cx300.event;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toMap;

import java.util.Map;

public enum CX300KeyType {
    KEYPAD_0_DOWN("0001"),
    KEYPAD_1_DOWN("0002"),
    KEYPAD_2_DOWN("0003"),
    KEYPAD_3_DOWN("0004"),
    KEYPAD_4_DOWN("0005"),
    KEYPAD_5_DOWN("0006"),
    KEYPAD_6_DOWN("0007"),
    KEYPAD_7_DOWN("0008"),
    KEYPAD_8_DOWN("0009"),
    KEYPAD_9_DOWN("000A"),
    KEYPAD_STAR_DOWN("000B"),
    KEYPAD_DASH_DOWN("000C"),
    REDIAL_DOWN("0400"),
    FLASH_DOWN("0200"),
    DELETE_DOWN("2000"),
    MUTE_DOWN("1000"),
    HOOK_UP("0100"),
    NO_KEY("0000");

    private static final Map<String, CX300KeyType> MAP =
            stream(CX300KeyType.values()).collect(toMap(cx300KeyType -> cx300KeyType.message, cx300KeyType -> cx300KeyType));

    private final String message;

    CX300KeyType(String message) {
        this.message = message;
    }

    public static CX300KeyType of(String message) {
        return MAP.get(message);
    }
}
