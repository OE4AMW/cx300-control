package com.bsmulders.cx300control.cx300.event;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

@Component
public class CX300MutedService {

    private final Map<String, Boolean> map = new HashMap<>();

    @PostConstruct
    public void fillMap() {
        map.put("01", true);
        map.put("00", false);
    }

    public boolean lookup(String hex) {
        return map.get(hex.substring(14, 16));
    }

}
