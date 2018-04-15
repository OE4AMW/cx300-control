package com.bsmulders.cx300control.cx300.control;

import static org.mockito.Mockito.verify;

import com.bsmulders.cx300control.hid.HidDeviceCommunicator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CX300LedTest {

    @Mock
    private HidDeviceCommunicator hidDeviceCommunicator;

    @InjectMocks
    private CX300Led cx300Led;

    @Test
    public void set() {
        cx300Led.set(CX300Led.STATUS_AVAILABLE);
        verify(hidDeviceCommunicator).sendMessage(new byte[]{0x16, 0x01});
    }

    @Test
    public void setMicrophone() {
        cx300Led.set(CX300Led.STATUS_BUSY, CX300Led.MICROPHONE_ON);
        verify(hidDeviceCommunicator).sendMessage(new byte[]{0x16, 0x03, 0x10});
    }
}
