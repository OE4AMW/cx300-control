package com.bsmulders.cx300control.cx300.event;

import org.springframework.context.ApplicationEvent;

import lombok.Getter;

@Getter
public class CX300Event extends ApplicationEvent {

    public enum Key {
        KEYPAD_0_DOWN,
        KEYPAD_1_DOWN,
        KEYPAD_2_DOWN,
        KEYPAD_3_DOWN,
        KEYPAD_4_DOWN,
        KEYPAD_5_DOWN,
        KEYPAD_6_DOWN,
        KEYPAD_7_DOWN,
        KEYPAD_8_DOWN,
        KEYPAD_9_DOWN,
        KEYPAD_STAR_DOWN,
        KEYPAD_DASH_DOWN,
        REDIAL_DOWN,
        FLASH_DOWN,
        DELETE_DOWN,
        MUTE_DOWN,
        HOOK_UP,
        NO_KEY,
        UNKNOWN
    }

    public enum AudioDevice {
        HANDSET,
        SPEAKER,
        HEADSET,
        NO_CHANGE,
        SPEAKER_LOUD,
        SPEAKER_VERY_LOUD
    }

    private final String data;
    private final Key key;
    private final boolean audioEnabled;
    private final boolean audioInEnabled;
    private final AudioDevice audioDevice;
    private final int volume;
    private final boolean muted;

    public CX300Event(Object source, String data, Key key, boolean audioEnabled, boolean audioInEnabled,
                      AudioDevice audioDevice, int volume, boolean muted) {
        super(source);
        this.data = data;
        this.key = key;
        this.audioEnabled = audioEnabled;
        this.audioInEnabled = audioInEnabled;
        this.audioDevice = audioDevice;
        this.volume = volume;
        this.muted = muted;
    }
}
