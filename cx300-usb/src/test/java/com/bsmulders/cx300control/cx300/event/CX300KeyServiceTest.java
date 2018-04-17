package com.bsmulders.cx300control.cx300.event;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class CX300KeyServiceTest {

    private CX300KeyService cx300KeyService;

    @Before
    public void setup() {
        cx300KeyService = new CX300KeyService();
        cx300KeyService.fillMap();
    }

    @Test
    public void lookup() {
        assertEquals(CX300Event.Key.KEYPAD_6_DOWN, cx300KeyService.lookup("01000703604E4001"));
    }
}
