package com.bsmulders.cx300control.cx300;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CX300ServiceUSB implements CX300Service {

    private final CX300Display cx300Display;
    private final CX300Setup cx300Setup;
    private final CX300Speakerphone cx300Speakerphone;
    private final CX300Status cx300Status;

    @Autowired
    public CX300ServiceUSB(CX300Display cx300Display, CX300Setup cx300Setup,
                           CX300Speakerphone cx300Speakerphone, CX300Status cx300Status) {
        this.cx300Display = cx300Display;
        this.cx300Setup = cx300Setup;
        this.cx300Speakerphone = cx300Speakerphone;
        this.cx300Status = cx300Status;
    }

    @Override
    public void setLanguage(Language language) {
        cx300Setup.setLanguage(language);
    }

    @Override
    public void setDisplayText(String text) {
        cx300Display.setText(text, "");
    }

    @Override
    public void setDisplayText(String top, String bottom) {
        cx300Display.setText(top, bottom);
    }

    @Override
    public void setDisplayText(String topLeft, String bottomLeft, String topRight, String bottomRight) {
        cx300Display.setText(topLeft, bottomLeft, topRight, bottomRight);
    }

    @Override
    public void clearDisplay() {
        cx300Display.clear();
    }

    @Override
    public void setStatus(Status status) {
        cx300Status.setStatus(status);
    }

    @Override
    public void setStatus(Status status, boolean microphone) {
        cx300Status.setStatus(status, microphone);
    }

    @Override
    public void enableSpeakerphone() {
        cx300Speakerphone.enable();
    }

    @Override
    public void disableSpeakerphone() {
        cx300Speakerphone.disable();
    }
}
