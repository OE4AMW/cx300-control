package com.bsmulders.cx300control.cx300;

import static org.junit.Assert.assertArrayEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import javax.xml.bind.DatatypeConverter;

import com.bsmulders.cx300control.hid.HidService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CX300DisplayTest {

    @Mock
    private HidService hidService;

    @InjectMocks
    private CX300Display cx300Display;

    @Test
    public void clear() {
        cx300Display.clear();
        verify(hidService).sendMessage(DatatypeConverter.parseHexBinary("1300"));
    }

    @Test
    public void setTextDouble() {
        cx300Display.setText("Top", "Bottom");

        ArgumentCaptor<byte[]> argument = ArgumentCaptor.forClass(byte[].class);
        verify(hidService, times(5)).sendMessage(argument.capture());

        assertArrayEquals(argument.getAllValues().get(0), DatatypeConverter.parseHexBinary("1315"));

        assertArrayEquals(argument.getAllValues().get(1), DatatypeConverter.parseHexBinary("140580"));
        assertArrayEquals(argument.getAllValues().get(2),DatatypeConverter.parseHexBinary("158054006f00700000000000000000000000"));

        assertArrayEquals(argument.getAllValues().get(3), DatatypeConverter.parseHexBinary("140a80"));
        assertArrayEquals(argument.getAllValues().get(4),DatatypeConverter.parseHexBinary("158042006f00740074006f006d0000000000"));
    }

    @Test
    public void setTextCorners() {
        cx300Display.setText("TopL", "BottomL", "TopR", "BottomR");

        ArgumentCaptor<byte[]> argument = ArgumentCaptor.forClass(byte[].class);
        verify(hidService, times(9)).sendMessage(argument.capture());

        assertArrayEquals(argument.getAllValues().get(0), DatatypeConverter.parseHexBinary("130d"));

        assertArrayEquals(argument.getAllValues().get(1), DatatypeConverter.parseHexBinary("140180"));
        assertArrayEquals(argument.getAllValues().get(2),DatatypeConverter.parseHexBinary("158054006f0070004c000000000000000000"));

        assertArrayEquals(argument.getAllValues().get(3), DatatypeConverter.parseHexBinary("140280"));
        assertArrayEquals(argument.getAllValues().get(4),DatatypeConverter.parseHexBinary("158042006f00740074006f006d004c000000"));

        assertArrayEquals(argument.getAllValues().get(5), DatatypeConverter.parseHexBinary("140380"));
        assertArrayEquals(argument.getAllValues().get(6),DatatypeConverter.parseHexBinary("158054006f00700052000000000000000000"));

        assertArrayEquals(argument.getAllValues().get(7), DatatypeConverter.parseHexBinary("140480"));
        assertArrayEquals(argument.getAllValues().get(8),DatatypeConverter.parseHexBinary("158042006f00740074006f006d0052000000"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void setTextDoubleLong() {
        cx300Display.setText("123456789012345678901234567890", "123456789012345678901234567890");
    }

    @Test(expected = IllegalArgumentException.class)
    public void setTextCornersLong() {
        cx300Display.setText("12345678901234567", "12345678901234567", "12345678901234567", "12345678901234567");
    }

}
