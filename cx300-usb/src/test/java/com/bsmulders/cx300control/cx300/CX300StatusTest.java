package com.bsmulders.cx300control.cx300;

import static org.mockito.Mockito.verify;

import com.bsmulders.cx300control.hid.HidService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CX300StatusTest {

    @Mock
    private HidService hidService;

    @InjectMocks
    private CX300Status cx300Status;

    @Test
    public void setStatus() {
        cx300Status.setStatus(CX300Service.Status.AVAILABLE);
        verify(hidService).sendMessage(new byte[]{0x16, 0x01});
    }

    @Test
    public void setStatusMicrophone() {
        cx300Status.setStatus(CX300Service.Status.BUSY, true);
        verify(hidService).sendMessage(new byte[]{0x16, 0x03, 0x10});
    }
}
