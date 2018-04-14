package com.bsmulders.cx300control.hid;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.hid4java.HidDevice;
import org.hid4java.HidManager;
import org.hid4java.HidServices;
import org.hid4java.HidServicesListener;
import org.hid4java.HidServicesSpecification;
import org.hid4java.ScanMode;
import org.hid4java.event.HidServicesEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class HidServiceListener implements HidServicesListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(HidServicesListener.class);

    private final HidDeviceCommunicator hidDeviceCommunicator;

    private HidServices hidServices;

    @Value("${usb.vendor.id}")
    private int vendorId;

    @Value("${usb.product.id}")
    private int productId;

    @Autowired
    public HidServiceListener(HidDeviceCommunicator hidDeviceCommunicator) {
        this.hidDeviceCommunicator = hidDeviceCommunicator;
    }

    @PostConstruct
    public void setup() {
        HidServicesSpecification hidServicesSpecification = new HidServicesSpecification();
        hidServicesSpecification.setAutoShutdown(true);
        hidServicesSpecification.setScanInterval(500);
        hidServicesSpecification.setPauseInterval(5000);
        hidServicesSpecification.setScanMode(ScanMode.SCAN_AT_FIXED_INTERVAL_WITH_PAUSE_AFTER_WRITE);

        hidServices = HidManager.getHidServices(hidServicesSpecification);
        hidServices.addHidServicesListener(this);
        hidServices.start();

        HidDevice hidDevice = hidServices.getHidDevice(vendorId, productId, null);
        if (hidDevice != null) {
            hidDeviceCommunicator.setHidDevice(hidDevice);
        }
    }

    @PreDestroy
    public void destroy() {
        hidServices.shutdown();
    }

    @Override
    public void hidDeviceAttached(HidServicesEvent event) {
        LOGGER.info("USB HID device attached: {}", event);

        if (event.getHidDevice()
                 .getVendorId() == vendorId
            && event.getHidDevice()
                    .getProductId() == productId) {
            hidDeviceCommunicator.setHidDevice(event.getHidDevice());
        }
    }

    @Override
    public void hidDeviceDetached(HidServicesEvent event) {
        LOGGER.info("USB HID device detached: {}", event);
        hidDeviceCommunicator.clearHidDevice();
    }

    @Override
    public void hidFailure(HidServicesEvent event) {
        LOGGER.error("USB HID failure: {}", event);
    }
}
