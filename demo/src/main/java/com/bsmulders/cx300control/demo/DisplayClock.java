package com.bsmulders.cx300control.demo;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_TIME;

import java.time.LocalTime;

import javax.annotation.PreDestroy;

import com.bsmulders.cx300control.cx300.CX300Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DisplayClock {

    private final CX300Service cx300Service;

    @Autowired
    public DisplayClock(CX300Service cx300Service) {
        this.cx300Service = cx300Service;
    }

    @Scheduled(fixedRate = 1000)
    public void displayClock() {
        LocalTime now = LocalTime.now()
                                 .withNano(0);

        cx300Service.setDisplayText("Time: ", now.format(ISO_LOCAL_TIME));
    }

    @PreDestroy
    public void clearScreen() {
        cx300Service.clearDisplay();
    }
}
