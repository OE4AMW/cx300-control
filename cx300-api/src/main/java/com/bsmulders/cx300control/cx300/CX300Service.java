package com.bsmulders.cx300control.cx300;

import org.springframework.stereotype.Component;

@Component
public interface CX300Service {

    enum Status {
        SIGN_IN, AVAILABLE, BUSY, BE_RIGHT_BACK, AWAY, DO_NOT_DISTURB, OFF_WORK
    }

    enum Language {
        ENGLISH, FRENCH, GERMAN, SPANISH
    }

    /**
     * Set the language of the Polycom CX300 phone
     *
     * @param language The language to use
     */
    void setLanguage(Language language);

    /**
     * Display text on the top line of the display
     *
     * @param text The text to display
     */
    void setDisplayText(String text);

    /**
     * Display text on the top and bottom line of the display
     *
     * @param top The text to display on the top line
     * @param bottom The text to display on the bottom line
     */
    void setDisplayText(String top, String bottom);

    /**
     * Display text in all four corners of the display
     *
     * @param topLeft The text to display in the top left corner
     * @param bottomLeft The text to display in the bottom left corner
     * @param topRight The text to display in the top right corner
     * @param bottomRight The text to display in the bottom right corner
     */
    void setDisplayText(String topLeft, String bottomLeft, String topRight, String bottomRight);

    /**
     * Clear all text from the display
     */
    void clearDisplay();

    /**
     * Set the status of the phone, according to Skype status types.
     * Will light up the presence LED on some status
     *
     * @param status The status to switch to
     */
    void setStatus(Status status);

    /**
     * Set the status of the phone, according to Skype status types. Also set the microphone on or off (including the LED)
     * Will light up the presence LED on some status
     *
     * @param status The status to switch to
     * @param microphone If the microphone should be enabled or not
     */
    void setStatus(Status status, boolean microphone);

    /**
     * Enable speakerphone and light up LED
     */
    void enableSpeakerphone();

    /**
     * Disable speakerphone and dim LED
     */
    void disableSpeakerphone();

}
