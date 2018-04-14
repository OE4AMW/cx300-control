package com.bsmulders.cx300control.hid;

import javax.xml.bind.DatatypeConverter;

import org.hid4java.HidDevice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HidDeviceCommunicator {

    private static final Logger LOGGER = LoggerFactory.getLogger(HidDeviceCommunicator.class);
    private static final int PACKET_LENGTH = 8;
    private static final byte[] USB_INITIAL = new byte[]{0x09, 0x04, 0x01, 0x02};
    private static final byte REPORT_ID = 0x17;

    private final HidDeviceReceiver hidDeviceReceiver;

    private HidDevice hidDevice;

    @Autowired
    public HidDeviceCommunicator(HidDeviceReceiver hidDeviceReceiver) {
        this.hidDeviceReceiver = hidDeviceReceiver;
    }

    public void setHidDevice(HidDevice hidDevice) {
        if (hidDevice == null) {
            return;
        }

        this.hidDevice = hidDevice;
        sendFeatureReport(USB_INITIAL, REPORT_ID);
        hidDeviceReceiver.start(hidDevice);
    }

    public void clearHidDevice() {
        this.hidDevice = null;
    }

    public void sendMessage(byte[] data) {
        if (hidDevice == null) {
            LOGGER.warn("Not sending data because HidDevice is not set: {}", DatatypeConverter.printHexBinary(data));
            return;
        }

        if (!hidDevice.isOpen()) {
            hidDevice.open();
        }

        int val = hidDevice.write(data, data.length, (byte) 0x00);
        if (val < 0) {
            LOGGER.error(hidDevice.getLastErrorMessage());
        }

        waitForData();
    }

    public void sendFeatureReport(byte[] data, byte reportid) {
        if (hidDevice == null) {
            LOGGER.warn("Not sending data because HidDevice is not set: {}", DatatypeConverter.printHexBinary(data));
            return;
        }

        if (!hidDevice.isOpen()) {
            hidDevice.open();
        }

        int val = hidDevice.sendFeatureReport(data, reportid);
        if (val < 0) {
            LOGGER.error(hidDevice.getLastErrorMessage());
        }

        waitForData();
    }

    private void waitForData() {
        boolean moreData = true;
        int val = 0;
        while (moreData) {
            byte[] data = new byte[PACKET_LENGTH];
            val = hidDevice.read(data, 1);
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
}

