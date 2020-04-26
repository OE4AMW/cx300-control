package com.bsmulders.cx300control.hid.event;

import javax.xml.bind.DatatypeConverter;
import org.hid4java.HidDevice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class HidDataEventDistributor {

    private static final Logger LOGGER = LoggerFactory.getLogger(HidDataEventDistributor.class);
    private static final int PACKET_LENGTH = 8;

    private final ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    public HidDataEventDistributor(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Async
    public void start(HidDevice hidDevice) {
        LOGGER.info("Started listening to USB HID device {}", hidDevice);

        byte[] data = new byte[PACKET_LENGTH];
        boolean listening = true;
        while (listening) {
            int val = hidDevice.read(data, 500);

            if (val > 0) {
                try {
                    HidDataEvent hidEvent = new HidDataEvent(this, data);
                    LOGGER.debug("New HID event: {}", hidEvent.getHex());
                    applicationEventPublisher.publishEvent(hidEvent);
                } catch (RuntimeException ex) {
                    LOGGER.error("could not crate Event from " + DatatypeConverter.printHexBinary(data) );
                    ex.printStackTrace();
                }
            } else if (val < 0) {
                listening = false;
            }
        }

        LOGGER.info("Stopped listening to USB HID device {}", hidDevice);
    }
}
