package com.bsmulders.cx300control.cx300.event;

import static org.junit.Assert.assertEquals;

import javax.xml.bind.DatatypeConverter;

import com.bsmulders.cx300control.hid.HidEvent;

import org.junit.Before;
import org.junit.Test;

public class CX300EventTest {

    private HidEvent hidEvent;
    private CX300Event cx300Event;

    @Before
    public void setup() {
        hidEvent = new HidEvent(this, DatatypeConverter.parseHexBinary("01000703604e4001"));
        cx300Event = new CX300Event(hidEvent);
    }

    @Test
    public void getKeyType() {
        assertEquals(CX300KeyType.KEYPAD_6_DOWN, cx300Event.getKeyType());
    }

    @Test
    public void getAudioInput() {
        assertEquals(CX300AudioInputType.ENABLED, cx300Event.getAudioInput());
    }

    @Test
    public void getAudioDeviceType() {
        assertEquals(CX300AudioDeviceType.HEADSET, cx300Event.getAudioDeviceType());
    }

    @Test
    public void getVolumeType() {
        assertEquals(CX300VolumeType.VOLUME_06, cx300Event.getVolumeType());
    }

    @Test
    public void getMute() {
        assertEquals(CX300MuteType.MUTED, cx300Event.getMute());
    }
}
