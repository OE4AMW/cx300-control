package com.bsmulders.cx300control.cx300.event;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

@Component
public class CX300AudioEnabledService {

    private final Map<String, Boolean> map = new HashMap<>();

    @PostConstruct
    public void fillMap() {
        map.put("03", true);
        map.put("00", false);
    }

    public boolean lookup(String hex) {
        return map.get(hex.substring(6, 8));
    }

}
