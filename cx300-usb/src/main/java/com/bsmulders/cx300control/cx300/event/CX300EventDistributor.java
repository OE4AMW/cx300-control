package com.bsmulders.cx300control.cx300.event;

import com.bsmulders.cx300control.hid.event.HidDataEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class CX300EventDistributor implements ApplicationListener<HidDataEvent> {

    private final ApplicationEventPublisher applicationEventPublisher;

    private final CX300KeyService cx300KeyService;
    private final CX300AudioEnabledService cx300AudioEnabledService;
    private final CX300AudioInEnabledService cx300AudioInEnabledService;
    private final CX300AudioDeviceService cx300AudioDeviceService;
    private final CX300VolumeService cx300VolumeService;
    private final CX300MutedService cx300MutedService;

    @Autowired
    public CX300EventDistributor(ApplicationEventPublisher applicationEventPublisher,
                                 CX300KeyService cx300KeyService,
                                 CX300AudioEnabledService cx300AudioEnabledService,
                                 CX300AudioInEnabledService cx300AudioInEnabledService,
                                 CX300AudioDeviceService cx300AudioDeviceService,
                                 CX300VolumeService cx300VolumeService,
                                 CX300MutedService cx300MutedService) {
        this.applicationEventPublisher = applicationEventPublisher;
        this.cx300KeyService = cx300KeyService;
        this.cx300AudioEnabledService = cx300AudioEnabledService;
        this.cx300AudioInEnabledService = cx300AudioInEnabledService;
        this.cx300AudioDeviceService = cx300AudioDeviceService;
        this.cx300VolumeService = cx300VolumeService;
        this.cx300MutedService = cx300MutedService;
    }


    @Override
    public void onApplicationEvent(HidDataEvent hidDataEvent) {
        String hex = hidDataEvent.getHex();
        CX300Event cx300Event = new CX300Event(this, hidDataEvent.getHex(),
                                               cx300KeyService.lookup(hex),
                                               cx300AudioEnabledService.lookup(hex),
                                               cx300AudioInEnabledService.lookup(hex),
                                               cx300AudioDeviceService.lookup(hex),
                                               cx300VolumeService.lookup(hex),
                                               cx300MutedService.lookup(hex));

        applicationEventPublisher.publishEvent(cx300Event);
    }
}
