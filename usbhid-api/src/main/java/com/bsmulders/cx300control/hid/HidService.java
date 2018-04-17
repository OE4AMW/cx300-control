package com.bsmulders.cx300control.hid;

import com.bsmulders.cx300control.hid.event.HidConnectedEvent;
import com.bsmulders.cx300control.hid.event.HidDataEvent;

import org.springframework.stereotype.Component;

@Component
public interface HidService {

    /**
     * Find a USB device based on the vendorId and productId. Will send a {@link HidConnectedEvent} immediately if a device is found or
     * when the device connects later. Will send a {@link HidDataEvent} if the requested device sends USB HID data
     *
     * @param vendorId The vendorId of the USB device to connect to
     * @param productId The productId of the USB device to connect to
     */
    void findDevice(int vendorId, int productId);

    /**
     * Send a HID feature report to the USB device, if one is connected
     *
     * @param report The byte array with the report to send
     * @param reportId The report identifier
     * @throws IllegalStateException If no device is connected
     */
    void sendFeatureReport(byte[] report, byte reportId);

    /**
     * Send a HID message to the USB device, if one is connected
     *
     * @param message The byte array of the message to send
     * @throws IllegalStateException If no device is connected
     */
    void sendMessage(byte[] message);

}
