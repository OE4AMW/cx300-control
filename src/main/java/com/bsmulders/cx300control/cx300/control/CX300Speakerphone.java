package com.bsmulders.cx300control.cx300.control;

import com.bsmulders.cx300control.hid.HidDeviceCommunicator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CX300Speakerphone {

    private static final byte GROUP = 0x02;

    public static final byte OFF = 0x00;
    public static final byte ON = 0x01;

    private final HidDeviceCommunicator hidDeviceCommunicator;

    @Autowired
    public CX300Speakerphone(HidDeviceCommunicator hidDeviceCommunicator) {
        this.hidDeviceCommunicator = hidDeviceCommunicator;
    }

    public void set(byte status) {
        hidDeviceCommunicator.sendMessage(new byte[]{GROUP, status});
    }
}
