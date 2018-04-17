package com.bsmulders.cx300control.cx300.event;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

@Component
public class CX300VolumeService {

    private final Map<String, Integer> map = new HashMap<>();

    @PostConstruct
    public void fillMap() {
        map.put("700B", 1);
        map.put("2710", 2);
        map.put("D116", 3);
        map.put("3B20", 4);
        map.put("862D", 5);
        map.put("4E40", 6);
        map.put("D55A", 7);
        map.put("4E80", 8);
        map.put("3CB5", 9);
        map.put("FFFF", 10);
    }

    public int lookup(String hex) {
        return map.get(hex.substring(10, 14));
    }

}
