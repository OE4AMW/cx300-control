package com.bsmulders.cx300control.cx300.event;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

@Component
public class CX300AudioDeviceService {

    private final Map<String, CX300Event.AudioDevice> map = new HashMap<>();

    @PostConstruct
    public void fillMap() {
        map.put("40", CX300Event.AudioDevice.HANDSET);
        map.put("50", CX300Event.AudioDevice.SPEAKER);
        map.put("60", CX300Event.AudioDevice.HEADSET);
        map.put("00", CX300Event.AudioDevice.NO_CHANGE);
        map.put("01", CX300Event.AudioDevice.SPEAKER_LOUD);
        map.put("02", CX300Event.AudioDevice.SPEAKER_VERY_LOUD);
    }

    public CX300Event.AudioDevice lookup(String hex) {
        return map.get(hex.substring(8, 10));
    }

}
