package com.bsmulders.cx300control.cx300.event;

import com.bsmulders.cx300control.hid.HidEvent;

public class CX300Event {

    private final HidEvent hidEvent;

    public CX300Event(HidEvent hidEvent) {
        this.hidEvent = hidEvent;
    }

    public CX300KeyType getKeyType() {
        return CX300KeyType.of(hidEvent.getHex()
                                       .substring(2, 6));
    }

    public CX300AudioInputType getAudioInput() {
        return CX300AudioInputType.of(hidEvent.getHex()
                                              .substring(6, 8));
    }

    public CX300AudioDeviceType getAudioDeviceType() {
        return CX300AudioDeviceType.of(hidEvent.getHex()
                                               .substring(8, 10));
    }

    public CX300VolumeType getVolumeType() {
        return CX300VolumeType.of(hidEvent.getHex()
                                          .substring(10, 14));
    }

    public CX300MuteType getMute() {
        return CX300MuteType.of(hidEvent.getHex()
                                        .substring(14, 16));
    }
}
