package com.bsmulders.cx300control.cx300;

import com.bsmulders.cx300control.hid.HidService;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;

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
    
    private static final byte COLOR_GREEN = 0x01;
    private static final byte COLOR_RED = 0x03;
    private static final byte COLOR_ORANGE_RED = 0x04;
    private static final byte COLOR_ORANGE = 0x05;
    private static final byte COLOR_GREEN_ORANGE = 0x08;
    private static final byte COLOR_MIX_ORANGE_RED = 0x0A;
    private static final byte COLOR_MIX_YELLOW_GREEN = 0x0B;
    private static final byte COLOR_RED_GREEN = 0x0C;
    private static final byte COLOR_MIX_YELLOW_ORANGE = 0x0E;

    private final HidService hidService;

    private final Map<CX300Service.Status, Byte> map = new HashMap<>();

    @PostConstruct
    public void fillMap() {
        map.put(CX300Service.Status.SIGN_IN, SIGN_IN);
        map.put(CX300Service.Status.AVAILABLE, AVAILABLE);
        map.put(CX300Service.Status.BUSY, BUSY);
        map.put(CX300Service.Status.BE_RIGHT_BACK, BE_RIGHT_BACK);
        map.put(CX300Service.Status.AWAY, AWAY);
        map.put(CX300Service.Status.DO_NOT_DISTURB, DO_NOT_DISTURB);
        map.put(CX300Service.Status.OFF_WORK, OFF_WORK);
        map.put(CX300Service.Status.COLOR_GREEN, COLOR_GREEN);
        map.put(CX300Service.Status.COLOR_RED, COLOR_RED);
        map.put(CX300Service.Status.COLOR_ORANGE_RED, COLOR_ORANGE_RED);
        map.put(CX300Service.Status.COLOR_ORANGE, COLOR_ORANGE);
        map.put(CX300Service.Status.COLOR_GREEN_ORANGE, COLOR_GREEN_ORANGE);
        map.put(CX300Service.Status.COLOR_MIX_ORANGE_RED, COLOR_MIX_ORANGE_RED);
        map.put(CX300Service.Status.COLOR_MIX_YELLOW_GREEN, COLOR_MIX_YELLOW_GREEN);
        map.put(CX300Service.Status.COLOR_RED_GREEN, COLOR_RED_GREEN);
        map.put(CX300Service.Status.COLOR_MIX_YELLOW_ORANGE, COLOR_MIX_YELLOW_ORANGE);
    }
    
    @Autowired
    public CX300Status(HidService hidService) {
        this.hidService = hidService;
    }

    void setStatus(CX300Service.Status status ) {
        byte statusByte = 0x00;
        try {
            statusByte = map.get(status);
        } catch (Exception e) {
            e.printStackTrace();
        }
        set(statusByte);
    }

    void setStatus(CX300Service.Status status, boolean microphone ) {
        byte microphoneData = microphone ? MICROPHONE_ON : MICROPHONE_OFF;
        byte statusByte = 0x00;
        try {
            statusByte = map.get(status);
        } catch (Exception e) {
            e.printStackTrace();
        }
        set(statusByte, microphoneData);
    }
    
    void setStatus(CX300Service.Status status, boolean microphone, boolean voicemail) {
        byte microphoneData = microphone ? MICROPHONE_ON : MICROPHONE_OFF;
        if (voicemail) {
            microphoneData += 0x02;
        }
        byte statusByte = 0x00;
        try {
            statusByte = map.get(status);
        } catch (Exception e) {
            e.printStackTrace();
        }
        set(statusByte, microphoneData);
    }

    private void set(byte status) {
        hidService.sendMessage(new byte[]{GROUP, status});
    }

    private void set(byte status, byte microphone) {
        hidService.sendMessage(new byte[]{GROUP, status, microphone});
    }

}
