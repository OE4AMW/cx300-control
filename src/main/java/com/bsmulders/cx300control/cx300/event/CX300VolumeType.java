package com.bsmulders.cx300control.cx300.event;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toMap;

import java.util.Map;

public enum CX300VolumeType {
    VOLUME_01("700B"),
    VOLUME_02("2710"),
    VOLUME_03("D116"),
    VOLUME_04("3B20"),
    VOLUME_05("862D"),
    VOLUME_06("4E40"),
    VOLUME_07("D55A"),
    VOLUME_08("4E80"),
    VOLUME_09("3CB5"),
    VOLUME_10("FFFF");

    private static final Map<String, CX300VolumeType> MAP =
            stream(CX300VolumeType.values()).collect(
                    toMap(cx300VolumeType -> cx300VolumeType.message, cx300VolumeType -> cx300VolumeType));

    private final String message;

    CX300VolumeType(String message) {
        this.message = message;
    }

    public static CX300VolumeType of(String message) {
        return MAP.get(message);
    }
}
