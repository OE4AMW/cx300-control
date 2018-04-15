package com.bsmulders.cx300control.cx300.control;

import com.bsmulders.cx300control.hid.HidDeviceCommunicator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CX300Led {

    private static final byte GROUP = 0x16;

    public static final byte STATUS_LED_GREEN = 0x01;
    public static final byte STATUS_LED_RED = 0x03;
    public static final byte STATUS_LED_YELLOW_RED = 0x04;
    public static final byte STATUS_LED_YELLOW = 0x05;
    public static final byte STATUS_LED_OFF = 0x07;

    public static final byte STATUS_AVAILABLE = 0x01;
    public static final byte STATUS_BUSY = 0x03;
    public static final byte STATUS_BE_RIGHT_BACK = 0x05;
    public static final byte STATUS_AWAY = 0x05;
    public static final byte STATUS_DO_NOT_DISTURB = 0x06;
    public static final byte STATUS_OFF_WORK = 0x07;

    public static final byte MICROPHONE_ON = 0x10;
    public static final byte MICROPHONE_OFF = 0x30;

    private final HidDeviceCommunicator hidDeviceCommunicator;

    @Autowired
    public CX300Led(HidDeviceCommunicator hidDeviceCommunicator) {
        this.hidDeviceCommunicator = hidDeviceCommunicator;
    }

    public void set(byte status) {
        hidDeviceCommunicator.sendMessage(new byte[]{GROUP, status});
    }

    public void set(byte status, byte microphone) {
        hidDeviceCommunicator.sendMessage(new byte[]{GROUP, status, microphone});
    }
}
