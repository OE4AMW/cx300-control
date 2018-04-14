package com.bsmulders.cx300control.hid;

import javax.xml.bind.DatatypeConverter;

import org.springframework.context.ApplicationEvent;

public class HidEvent extends ApplicationEvent {

    private final byte[] message;

    public HidEvent(Object source, byte[] message) {
        super(source);
        this.message = message;
    }

    public String getHex() {
        return DatatypeConverter.printHexBinary(message);
    }

}
