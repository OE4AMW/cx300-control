package com.bsmulders.cx300control.cx300.control;

import com.bsmulders.cx300control.hid.HidDeviceCommunicator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CX300Led {

    private static final byte[] STATUS_LED_ON = new byte[]{0x16, 0x01};
    private static final byte[] STATUS_LED_OFF = new byte[]{0x16, 0x00};
    private static final byte[] SPEAKERPHONE_LED_ON = new byte[]{0x02, 0x01};
    private static final byte[] SPEAKERPHONE_LED_OFF = new byte[]{0x02, 0x00};

    private final HidDeviceCommunicator hidDeviceCommunicator;

    @Autowired
    public CX300Led(HidDeviceCommunicator hidDeviceCommunicator) {
        this.hidDeviceCommunicator = hidDeviceCommunicator;
    }

    public void enableStatusLed() {
        hidDeviceCommunicator.sendMessage(STATUS_LED_ON);
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
