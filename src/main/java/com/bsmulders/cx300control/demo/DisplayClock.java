package com.bsmulders.cx300control.demo;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_TIME;

import java.time.LocalTime;

import javax.annotation.PreDestroy;

import com.bsmulders.cx300control.cx300.control.CX300Display;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DisplayClock {

    private final CX300Display cx300Display;

    @Autowired
    public DisplayClock(CX300Display cx300Display) {
        this.cx300Display = cx300Display;
    }

    @Scheduled(fixedRate = 1000)
    public void displayClock() {
        LocalTime now = LocalTime.now()
                                 .withNano(0);

        cx300Display.setText("Time: ", now.format(ISO_LOCAL_TIME));
    }

    @PreDestroy
    public void clearScreen() {
        cx300Display.clear();
    }
}
