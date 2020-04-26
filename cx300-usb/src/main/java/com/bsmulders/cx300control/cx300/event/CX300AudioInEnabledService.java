package com.bsmulders.cx300control.cx300.event;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

@Component
public class CX300AudioInEnabledService {

    private final Map<String, Boolean> map = new HashMap<>();

    @PostConstruct
    public void fillMap() {
        map.put("00", false);
        map.put("01", true);   // Mic On && Audio Out Off
        map.put("02", false);
        map.put("03", true);   // Mic On && Audio Out On
    }

    public boolean lookup(String hex) {
        return map.get(hex.substring(6, 8));
    }

}
