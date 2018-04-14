package com.bsmulders.cx300control.cx300.control;

import com.bsmulders.cx300control.hid.HidDeviceCommunicator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CX300Led {

    private static final byte[] STATUS_LED_OFF          = new byte[]{0x16, 0x00};
    private static final byte[] STATUS_LED_GREEN        = new byte[]{0x16, 0x01};
    private static final byte[] STATUS_LED_RED          = new byte[]{0x16, 0x03};
    private static final byte[] STATUS_LED_YELLOW_RED   = new byte[]{0x16, 0x04};
    private static final byte[] STATUS_LED_YELLOW       = new byte[]{0x16, 0x05};
    private static final byte[] SPEAKERPHONE_LED_OFF    = new byte[]{0x02, 0x00};
    private static final byte[] SPEAKERPHONE_LED_ON     = new byte[]{0x02, 0x01};

    private final HidDeviceCommunicator hidDeviceCommunicator;

    @Autowired
    public CX300Led(HidDeviceCommunicator hidDeviceCommunicator) {
        this.hidDeviceCommunicator = hidDeviceCommunicator;
    }

    public void enableGreenStatusLed() {
        hidDeviceCommunicator.sendMessage(STATUS_LED_GREEN);
    }

    public void enableRedStatusLed() {
        hidDeviceCommunicator.sendMessage(STATUS_LED_RED);
    }

    public void enableYellowStatusLed() {
        hidDeviceCommunicator.sendMessage(STATUS_LED_YELLOW);
    }

    public void enableYellowRedStatusLed() {
        hidDeviceCommunicator.sendMessage(STATUS_LED_YELLOW_RED);
    }

    public void disableStatusLed() {
        hidDeviceCommunicator.sendMessage(STATUS_LED_OFF);
    }

    public void enableSpeakerphoneLed() {
        hidDeviceCommunicator.sendMessage(SPEAKERPHONE_LED_ON);
    }

    public void disableSpeakerphoneLed() {
        hidDeviceCommunicator.sendMessage(SPEAKERPHONE_LED_OFF);
    }
}
