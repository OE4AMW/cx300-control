package com.bsmulders.cx300control.cx300;

import static org.mockito.Mockito.verify;

import com.bsmulders.cx300control.hid.HidService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CX300SpeakerphoneTest {

    @Mock
    private HidService hidService;

    @InjectMocks
    private CX300Speakerphone cx300Speakerphone;

    @Test
    public void enable() {
        cx300Speakerphone.enable();
        verify(hidService).sendMessage(new byte[]{0x02, 0x01});
    }

    @Test
    public void disable() {
        cx300Speakerphone.disable();
        verify(hidService).sendMessage(new byte[]{0x02, 0x00});
    }

}
