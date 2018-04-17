package com.bsmulders.cx300control.hid.event;

import javax.xml.bind.DatatypeConverter;

import org.springframework.context.ApplicationEvent;

import lombok.Getter;

@Getter
public class HidDataEvent extends ApplicationEvent {

    private final byte[] message;

    public HidDataEvent(Object source, byte[] message) {
        super(source);
        this.message = message;
    }

    public String getHex() {
        return DatatypeConverter.printHexBinary(getMessage());
    }

}
