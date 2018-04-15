package com.bsmulders.cx300control.cx300.control;

import static org.mockito.Mockito.verify;

import com.bsmulders.cx300control.hid.HidDeviceCommunicator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CX300SpeakerphoneTest {

    @Mock
    private HidDeviceCommunicator hidDeviceCommunicator;

    @InjectMocks
    private CX300Speakerphone cx300Speakerphone;

    @Test
    public void set() {
        cx300Speakerphone.set(CX300Speakerphone.ON);
        verify(hidDeviceCommunicator).sendMessage(new byte[]{0x02, 0x01});
    }

}
