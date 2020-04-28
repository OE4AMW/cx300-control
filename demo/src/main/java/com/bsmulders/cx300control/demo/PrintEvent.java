package com.bsmulders.cx300control.demo;

import com.bsmulders.cx300control.cx300.event.CX300Event;

import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;

@Component
public class PrintEvent implements ApplicationListener<CX300Event> {

    private static final Logger LOGGER = LoggerFactory.getLogger(PrintEvent.class);
    
    private boolean xdoTool = false;
    
    @PostConstruct
    public void checkXdotool() {
        try {
            Process proc = Runtime.getRuntime().exec("xdotool -h");
            proc.waitFor();
            if (proc.exitValue() == 0) {
                xdoTool = true;
                LOGGER.info("xdotool available - keyboard-simulation enabled");
            } else {
                LOGGER.warn("xdotool NOT available - keyboard-simulation disabled");
            }
        } catch (IOException ex) {
            LOGGER.error("Exec-error occured", ex);
        } catch (InterruptedException ex) {
            LOGGER.error("Exec interrupted", ex);
        }
    }

    @Override
    public void onApplicationEvent(CX300Event cx300Event) {
        LOGGER.info("HID Data: {}", cx300Event.getData());
        LOGGER.info("Key: {}", cx300Event.getKey());
        LOGGER.info("Audio enabled: {}", cx300Event.isAudioEnabled());
        LOGGER.info("Audio In enabled: {}", cx300Event.isAudioInEnabled());
        LOGGER.info("Audio device: {}", cx300Event.getAudioDevice());
        LOGGER.info("Volume: {}", cx300Event.getVolume());
        LOGGER.info("Muted: {}", cx300Event.isMuted());

        if (null != cx300Event.getKey()) switch (cx300Event.getKey()) {
            case KEYPAD_1_DOWN:
                keyPress("1");
                break;
            case KEYPAD_2_DOWN:
                keyPress("2");
                break;
            case KEYPAD_3_DOWN:
                keyPress("3");
                break;
            case KEYPAD_4_DOWN:
                keyPress("4");
                break;
            case KEYPAD_5_DOWN:
                keyPress("5");
                break;
            case KEYPAD_6_DOWN:
                keyPress("6");
                break;
            case KEYPAD_7_DOWN:
                keyPress("7");
                break;
            case KEYPAD_8_DOWN:
                keyPress("8");
                break;
            case KEYPAD_9_DOWN:
                keyPress("9");
                break;
            case KEYPAD_0_DOWN:
                keyPress("0");
                break;
            case DELETE_DOWN:
                keyPress("BackSpace");
                break;
            case KEYPAD_STAR_DOWN:
                keyPress("asterisk");
                break;
            case KEYPAD_DASH_DOWN:
                keyPress("numbersign");
                break;
            default:
                break;
        }

    }
    
    private void keyPress(String key) {
        if (xdoTool) {
            try {
                Runtime.getRuntime().exec("xdotool key " + key);
            } catch (IOException ex) {
                LOGGER.error("xdotool exec error!", ex);
            }
        }
    }
}
