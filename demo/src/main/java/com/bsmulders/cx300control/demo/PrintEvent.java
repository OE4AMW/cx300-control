package com.bsmulders.cx300control.demo;

import com.bsmulders.cx300control.cx300.event.CX300Event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class PrintEvent implements ApplicationListener<CX300Event> {

    private static final Logger LOGGER = LoggerFactory.getLogger(PrintEvent.class);

    @Override
    public void onApplicationEvent(CX300Event cx300Event) {
        LOGGER.info("HID Data: {}", cx300Event.getData());
        LOGGER.info("Key: {}", cx300Event.getKey());
        LOGGER.info("Audio enabled: {}", cx300Event.isAudioEnabled());
        LOGGER.info("Audio In enabled: {}", cx300Event.isAudioInEnabled());
        LOGGER.info("Audio device: {}", cx300Event.getAudioDevice());
        LOGGER.info("Volume: {}", cx300Event.getVolume());
        LOGGER.info("Muted: {}", cx300Event.isMuted());
    }
}
