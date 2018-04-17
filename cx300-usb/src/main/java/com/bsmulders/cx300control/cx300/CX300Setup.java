package com.bsmulders.cx300control.cx300;

import javax.annotation.PostConstruct;

import com.bsmulders.cx300control.hid.HidService;
import com.bsmulders.cx300control.hid.event.HidConnectedEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class CX300Setup implements ApplicationListener<HidConnectedEvent> {

    private static final int VENDOR_ID = 2397;
    private static final int PRODUCT_ID = -28159;

    private static final byte LANGUAGE_EN = 0x09;
    private static final byte LANGUAGE_DE = 0x07;
    private static final byte LANGUAGE_FR = 0x0C;
    private static final byte LANGUAGE_ES = 0x0A;

    private static final byte SETTING_2_DEFAULT = 0x04;
    private static final byte SETTING_3_DEFAULT = 0x01;
    private static final byte SETTING_4_DEFAULT = 0x02;
    private static final byte REPORT_ID = 0x17;

    private final HidService hidService;
    private byte language;

    @Autowired
    public CX300Setup(HidService hidService) {
        this.hidService = hidService;
        this.language = LANGUAGE_EN;
    }

    @PostConstruct
    public void setup() {
        hidService.findDevice(VENDOR_ID, PRODUCT_ID);
    }

    void setLanguage(CX300Service.Language language) {
        switch(language) {
            case ENGLISH:
                this.language = LANGUAGE_EN;
                break;
            case FRENCH:
                this.language = LANGUAGE_FR;
                break;
            case GERMAN:
                this.language = LANGUAGE_DE;
                break;
            case SPANISH:
                this.language = LANGUAGE_ES;
                break;
        }

        sendFeatureReport();
    }

    @Override
    public void onApplicationEvent(HidConnectedEvent hidConnectedEvent) {
        if (hidConnectedEvent.getVendorId() == VENDOR_ID
            && hidConnectedEvent.getProductId() == PRODUCT_ID) {
            sendFeatureReport();
        }
    }

    private void sendFeatureReport() {
        hidService.sendFeatureReport(new byte[]{language,
                                             SETTING_2_DEFAULT,
                                             SETTING_3_DEFAULT,
                                             SETTING_4_DEFAULT},
                                     REPORT_ID);
    }
}
