package com.bsmulders.cx300control.cx300;

import com.bsmulders.cx300control.hid.HidService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CX300Status {

    private static final byte GROUP = 0x16;

    private static final byte SIGN_IN = 0x00;
    private static final byte AVAILABLE = 0x01;
    private static final byte BUSY = 0x03;
    private static final byte BE_RIGHT_BACK = 0x04;
    private static final byte AWAY = 0x05;
    private static final byte DO_NOT_DISTURB = 0x06;
    private static final byte OFF_WORK = 0x07;

    private static final byte MICROPHONE_ON = 0x10;
    private static final byte MICROPHONE_OFF = 0x30;

    private final HidService hidService;

    @Autowired
    public CX300Status(HidService hidService) {
        this.hidService = hidService;
    }

    void setStatus(CX300Service.Status status) {
        switch (status) {
            case SIGN_IN:
                set(SIGN_IN);
                break;
            case AVAILABLE:
                set(AVAILABLE);
                break;
            case BUSY:
                set(BUSY);
                break;
            case BE_RIGHT_BACK:
                set(BE_RIGHT_BACK);
                break;
            case AWAY:
                set(AWAY);
                break;
            case DO_NOT_DISTURB:
                set(DO_NOT_DISTURB);
                break;
            case OFF_WORK:
                set(OFF_WORK);
                break;
        }
    }

    void setStatus(CX300Service.Status status, boolean microphone) {
        byte microphoneData = microphone ? MICROPHONE_ON : MICROPHONE_OFF;

        switch (status) {
            case SIGN_IN:
                set(SIGN_IN, microphoneData);
                break;
            case AVAILABLE:
                set(AVAILABLE, microphoneData);
                break;
            case BUSY:
                set(BUSY, microphoneData);
                break;
            case BE_RIGHT_BACK:
                set(BE_RIGHT_BACK, microphoneData);
                break;
            case AWAY:
                set(AWAY, microphoneData);
                break;
            case DO_NOT_DISTURB:
                set(DO_NOT_DISTURB, microphoneData);
                break;
            case OFF_WORK:
                set(OFF_WORK, microphoneData);
                break;
        }
    }

    private void set(byte status) {
        hidService.sendMessage(new byte[]{GROUP, status});
    }

    private void set(byte status, byte microphone) {
        hidService.sendMessage(new byte[]{GROUP, status, microphone});
    }
}
