package com.bsmulders.cx300control.demo;

import com.bsmulders.cx300control.cx300.control.CX300Display;
import com.bsmulders.cx300control.cx300.control.CX300Led;
import com.bsmulders.cx300control.cx300.control.CX300Speakerphone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LedTest {

    private final CX300Display cx300Display;
    private final CX300Led cx300Led;
    private final CX300Speakerphone cx300Speakerphone;

    @Autowired
    public LedTest(CX300Display cx300Display, CX300Led cx300Led,
                   CX300Speakerphone cx300Speakerphone) {
        this.cx300Display = cx300Display;
        this.cx300Led = cx300Led;
        this.cx300Speakerphone = cx300Speakerphone;
    }

    //@Scheduled(fixedDelay = 2000)
    public void testLeds() throws InterruptedException {
        cx300Display.setText("Current test:", "STATUS_LED_GREEN");
        cx300Led.set(CX300Led.STATUS_LED_GREEN);
        Thread.sleep(2000);

        cx300Display.setText("Current test:", "STATUS_LED_RED");
        cx300Led.set(CX300Led.STATUS_LED_RED);
        Thread.sleep(2000);

        cx300Display.setText("Current test:", "STATUS_LED_YELLOW_RED");
        cx300Led.set(CX300Led.STATUS_LED_YELLOW_RED);
        Thread.sleep(2000);

        cx300Display.setText("Current test:", "STATUS_LED_YELLOW");
        cx300Led.set(CX300Led.STATUS_LED_YELLOW);
        Thread.sleep(2000);

        cx300Display.setText("Current test:", "STATUS_LED_OFF");
        cx300Led.set(CX300Led.STATUS_LED_OFF);
        Thread.sleep(2000);

        cx300Display.setText("Current test:", "STATUS_AVAILABLE");
        cx300Led.set(CX300Led.STATUS_AVAILABLE);
        Thread.sleep(2000);

        cx300Display.setText("Current test:", "STATUS_BUSY");
        cx300Led.set(CX300Led.STATUS_BUSY);
        Thread.sleep(2000);

        cx300Display.setText("Current test:", "STATUS_BE_RIGHT_BACK");
        cx300Led.set(CX300Led.STATUS_BE_RIGHT_BACK);
        Thread.sleep(2000);

        cx300Display.setText("Current test:", "STATUS_AWAY");
        cx300Led.set(CX300Led.STATUS_AWAY);
        Thread.sleep(2000);

        cx300Display.setText("Current test:", "STATUS_DO_NOT_DISTURB");
        cx300Led.set(CX300Led.STATUS_DO_NOT_DISTURB);
        Thread.sleep(2000);

        cx300Display.setText("Current test:", "STATUS_OFF_WORK");
        cx300Led.set(CX300Led.STATUS_OFF_WORK);
        Thread.sleep(2000);

        cx300Display.setText("Current test:", "MICROPHONE_ON");
        cx300Led.set(CX300Led.STATUS_LED_OFF, CX300Led.MICROPHONE_ON);
        Thread.sleep(2000);

        cx300Display.setText("Current test:", "MICROPHONE_OFF");
        cx300Led.set(CX300Led.STATUS_LED_OFF, CX300Led.MICROPHONE_OFF);
        Thread.sleep(2000);

        cx300Display.setText("Current test:", "SPEAKERPHONE_ON");
        cx300Speakerphone.set(CX300Speakerphone.ON);
        Thread.sleep(2000);

        cx300Display.setText("Current test:", "SPEAKERPHONE_OFF");
        cx300Speakerphone.set(CX300Speakerphone.OFF);
        Thread.sleep(2000);
    }

}
