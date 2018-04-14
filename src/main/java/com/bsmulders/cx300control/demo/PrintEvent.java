package com.bsmulders.cx300control.demo;

import com.bsmulders.cx300control.cx300.event.CX300Event;
import com.bsmulders.cx300control.hid.HidEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class PrintEvent implements ApplicationListener<HidEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(PrintEvent.class);

    @Override
    public void onApplicationEvent(HidEvent hidEvent) {
        CX300Event cx300Event = new CX300Event(hidEvent);

        LOGGER.info("Key type: {}", cx300Event.getKeyType());
        if (cx300Event.getKeyType() == null) {
            LOGGER.error("No key: {}", hidEvent.getHex()
                                               .substring(2, 6));
        }

        LOGGER.info("Audio input: {}", cx300Event.getAudioInput());
        if (cx300Event.getAudioInput() == null) {
            LOGGER.error("No audio input: {}", hidEvent.getHex()
                                                       .substring(6, 8));
        }

        LOGGER.info("Audio device type: {}", cx300Event.getAudioDeviceType());
        if (cx300Event.getAudioDeviceType() == null) {
            LOGGER.error("No audio device type: {}", hidEvent.getHex()
                                                             .substring(8, 10));
        }

        LOGGER.info("Volume type: {}", cx300Event.getVolumeType());
        if (cx300Event.getVolumeType() == null) {
            LOGGER.error("No volume: {}", hidEvent.getHex()
                                                  .substring(10, 14));
        }

        LOGGER.info("Muted: {}", cx300Event.getMute());
        if (cx300Event.getMute() == null) {
            LOGGER.error("No mute: {}", hidEvent.getHex()
                                                .substring(14, 16));
        }
    }
}
