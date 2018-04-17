package com.bsmulders.cx300control.cx300;

import com.bsmulders.cx300control.hid.HidService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CX300Speakerphone {

    private static final byte GROUP = 0x02;

    private static final byte OFF = 0x00;
    private static final byte ON = 0x01;

    private final HidService hidService;

    @Autowired
    public CX300Speakerphone(HidService hidService) {
        this.hidService = hidService;
    }

    void enable() {
        set(ON);
    }

    void disable() {
        set(OFF);
    }

    private void set(byte status) {
        hidService.sendMessage(new byte[]{GROUP, status});
    }
}
