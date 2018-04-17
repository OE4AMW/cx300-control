package com.bsmulders.cx300control.cx300.event;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

@Component
public class CX300KeyService {

    private final Map<String, CX300Event.Key> map = new HashMap<>();

    @PostConstruct
    public void fillMap() {
        map.put("0001", CX300Event.Key.KEYPAD_0_DOWN);
        map.put("0002", CX300Event.Key.KEYPAD_1_DOWN);
        map.put("0003", CX300Event.Key.KEYPAD_2_DOWN);
        map.put("0004", CX300Event.Key.KEYPAD_3_DOWN);
        map.put("0005", CX300Event.Key.KEYPAD_4_DOWN);
        map.put("0006", CX300Event.Key.KEYPAD_5_DOWN);
        map.put("0007", CX300Event.Key.KEYPAD_6_DOWN);
        map.put("0008", CX300Event.Key.KEYPAD_7_DOWN);
        map.put("0009", CX300Event.Key.KEYPAD_8_DOWN);
        map.put("000A", CX300Event.Key.KEYPAD_9_DOWN);
        map.put("000B", CX300Event.Key.KEYPAD_STAR_DOWN);
        map.put("000C", CX300Event.Key.KEYPAD_DASH_DOWN);
        map.put("0400", CX300Event.Key.REDIAL_DOWN);
        map.put("0200", CX300Event.Key.FLASH_DOWN);
        map.put("2000", CX300Event.Key.DELETE_DOWN);
        map.put("1000", CX300Event.Key.MUTE_DOWN);
        map.put("0100", CX300Event.Key.HOOK_UP);
        map.put("0000", CX300Event.Key.NO_KEY);
        map.put("4000", CX300Event.Key.UNKNOWN);
    }

    public CX300Event.Key lookup(String hex) {
        return map.get(hex.substring(2, 6));
    }

}
