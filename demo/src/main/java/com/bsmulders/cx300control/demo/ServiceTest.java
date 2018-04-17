package com.bsmulders.cx300control.demo;

import static com.bsmulders.cx300control.cx300.CX300Service.Language.ENGLISH;
import static com.bsmulders.cx300control.cx300.CX300Service.Language.SPANISH;
import static com.bsmulders.cx300control.cx300.CX300Service.Status.AVAILABLE;
import static com.bsmulders.cx300control.cx300.CX300Service.Status.AWAY;
import static com.bsmulders.cx300control.cx300.CX300Service.Status.BE_RIGHT_BACK;
import static com.bsmulders.cx300control.cx300.CX300Service.Status.DO_NOT_DISTURB;
import static com.bsmulders.cx300control.cx300.CX300Service.Status.OFF_WORK;
import static com.bsmulders.cx300control.cx300.CX300Service.Status.SIGN_IN;

import javax.annotation.PreDestroy;

import com.bsmulders.cx300control.cx300.CX300Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ServiceTest {

    private final CX300Service cx300Service;

    @Autowired
    public ServiceTest(CX300Service cx300Service) {
        this.cx300Service = cx300Service;
    }

    //@Scheduled(fixedDelay = 2000)
    public void testService() throws InterruptedException {
        cx300Service.setDisplayText("TOPL", "BOTTOML", "TOPR", "BOTTOMR");
        Thread.sleep(2000);

        cx300Service.setDisplayText("TOP", "BOTTOM");
        Thread.sleep(2000);

        cx300Service.setDisplayText("SIGN_IN");
        cx300Service.setStatus(SIGN_IN);
        Thread.sleep(2000);

        cx300Service.setDisplayText("AVAILABLE");
        cx300Service.setStatus(AVAILABLE);
        Thread.sleep(2000);

        cx300Service.setDisplayText("BUSY");
        cx300Service.setStatus(CX300Service.Status.BUSY);
        Thread.sleep(2000);

        cx300Service.setDisplayText("BE_RIGHT_BACK");
        cx300Service.setStatus(BE_RIGHT_BACK);
        Thread.sleep(2000);

        cx300Service.setDisplayText("AWAY");
        cx300Service.setStatus(AWAY);
        Thread.sleep(2000);

        cx300Service.setDisplayText("DO_NOT_DISTURB");
        cx300Service.setStatus(DO_NOT_DISTURB);
        Thread.sleep(2000);

        cx300Service.setDisplayText("OFF_WORK");
        cx300Service.setStatus(OFF_WORK);
        Thread.sleep(2000);

        cx300Service.setDisplayText("MICROPHONE_ON");
        cx300Service.setStatus(OFF_WORK, true);
        Thread.sleep(2000);

        cx300Service.setDisplayText("MICROPHONE_OFF");
        cx300Service.setStatus(OFF_WORK, false);
        Thread.sleep(2000);

        cx300Service.setDisplayText("SPEAKERPHONE_ON");
        cx300Service.enableSpeakerphone();
        Thread.sleep(2000);

        cx300Service.setDisplayText("SPEAKERPHONE_OFF");
        cx300Service.disableSpeakerphone();
        Thread.sleep(2000);

        cx300Service.setDisplayText("SPANISH");
        cx300Service.setLanguage(SPANISH);
        cx300Service.setStatus(SIGN_IN);
        cx300Service.setLanguage(ENGLISH);
        Thread.sleep(2000);
    }

    @PreDestroy
    public void clearScreen() {
        cx300Service.setStatus(OFF_WORK);
        cx300Service.clearDisplay();
    }


}
