package com.bsmulders.cx300control.hid.event;

import org.springframework.context.ApplicationEvent;

import lombok.Getter;

@Getter
public class HidConnectedEvent extends ApplicationEvent {

    private final int vendorId;
    private final int productId;

    public HidConnectedEvent(Object source, int vendorId, int productId) {
        super(source);
        this.vendorId = vendorId;
        this.productId = productId;
    }
}
