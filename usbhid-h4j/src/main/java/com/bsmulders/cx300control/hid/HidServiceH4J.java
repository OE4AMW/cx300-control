package com.bsmulders.cx300control.hid;

import javax.annotation.PostConstruct;
import javax.xml.bind.DatatypeConverter;

import com.bsmulders.cx300control.hid.event.HidConnectedEvent;
import com.bsmulders.cx300control.hid.event.HidDataEventDistributor;

import org.hid4java.HidDevice;
import org.hid4java.HidManager;
import org.hid4java.HidServices;
import org.hid4java.HidServicesListener;
import org.hid4java.event.HidServicesEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class HidServiceH4J implements HidService, HidServicesListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(HidServiceH4J.class);
    private static final int PACKET_LENGTH = 8;

    private final ApplicationEventPublisher applicationEventPublisher;
    private final HidDataEventDistributor hidDataEventDistributor;

    private HidServices hidServices;
    private HidDevice hidDevice;
    private int vendorId;
    private int productId;

    @Autowired
    public HidServiceH4J(ApplicationEventPublisher applicationEventPublisher, HidDataEventDistributor hidDataEventDistributor) {
        this.applicationEventPublisher = applicationEventPublisher;
        this.hidDataEventDistributor = hidDataEventDistributor;
    }

    @PostConstruct
    public void setup() {
        hidServices = HidManager.getHidServices();
        hidServices.addHidServicesListener(this);
        hidServices.start();
    }

    @Override
    public void findDevice(int vendorId, int productId) {
        this.vendorId = vendorId;
        this.productId = productId;

        setHidDevice(hidServices.getHidDevice(vendorId, productId, null));
    }

    @Override
    public void sendFeatureReport(byte[] report, byte reportId) {
        if (hidDevice == null) {
            LOGGER.debug("Not sending data because HidDevice is not set: {}", DatatypeConverter.printHexBinary(report));
            throw new IllegalStateException();
        }

        if (!hidDevice.isOpen()) {
            hidDevice.open();
        }

        int val = hidDevice.sendFeatureReport(report, reportId);
        if (val < 0) {
            LOGGER.error(hidDevice.getLastErrorMessage());
        }

        waitForData();
    }

    @Override
    public void sendMessage(byte[] message) {
        if (hidDevice == null) {
            LOGGER.debug("Not sending data because HidDevice is not set: {}", DatatypeConverter.printHexBinary(message));
            throw new IllegalStateException();
        }

        if (!hidDevice.isOpen()) {
            hidDevice.open();
        }

        int val = hidDevice.write(message, message.length, (byte) 0x00);
        if (val < 0) {
            LOGGER.error(hidDevice.getLastErrorMessage());
        }

        waitForData();
    }

    @Override
    public void hidDeviceAttached(HidServicesEvent event) {
        if (event.getHidDevice()
                 .getVendorId() == vendorId
            && event.getHidDevice()
                    .getProductId() == productId) {
            setHidDevice(event.getHidDevice());
        }
    }

    @Override
    public void hidDeviceDetached(HidServicesEvent event) {
        if (event.getHidDevice()
                 .getVendorId() == vendorId
            && event.getHidDevice()
                    .getProductId() == productId) {
            setHidDevice(null);
        }
    }

    @Override
    public void hidFailure(HidServicesEvent event) {
        LOGGER.error("USB HID failure: {}", event);
    }

    private void waitForData() {
        boolean moreData = true;

        while (moreData) {
            byte[] data = new byte[PACKET_LENGTH];
            int val = hidDevice.read(data, 1);
            switch (val) {
                case -1:
                    LOGGER.error(hidDevice.getLastErrorMessage());
                    moreData = false;
                    break;
                case 0:
                    moreData = false;
                    break;
                default:
                    LOGGER.warn("Did not expect data at this point: {}", DatatypeConverter.printHexBinary(data));
                    break;
            }
        }
    }

    private void setHidDevice(HidDevice hidDevice) {
        this.hidDevice = hidDevice;

        if (hidDevice != null) {
            applicationEventPublisher.publishEvent(new HidConnectedEvent(this, hidDevice.getVendorId(), hidDevice.getProductId()));
            hidDataEventDistributor.start(hidDevice);
        }
    }
}
