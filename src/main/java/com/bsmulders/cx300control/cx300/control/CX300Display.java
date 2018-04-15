package com.bsmulders.cx300control.cx300.control;

import java.util.Arrays;

import com.bsmulders.cx300control.hid.HidDeviceCommunicator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CX300Display {

    private static final byte[] DISPLAY_CLEAR = new byte[]{0x13, 0x00};
    private static final byte[] INITIALISE_CORNERS = new byte[]{0x13, 0x0d};
    private static final byte[] INITIALISE_FULL = new byte[]{0x13, 0x15}; // 0x15
    private static final byte[] DISPLAY_TOP_LEFT = new byte[]{0x14, 0x01, (byte) 0x80};
    private static final byte[] DISPLAY_BOTTOM_LEFT = new byte[]{0x14, 0x02, (byte) 0x80};
    private static final byte[] DISPLAY_TOP_RIGHT = new byte[]{0x14, 0x03, (byte) 0x80};
    private static final byte[] DISPLAY_BOTTOM_RIGHT = new byte[]{0x14, 0x04, (byte) 0x80};
    private static final byte[] DISPLAY_TOP = new byte[]{0x14, 0x05, (byte) 0x80};
    private static final byte[] DISPLAY_BOTTOM = new byte[]{0x14, 0x0a, (byte) 0x80};
    private static final byte[] TEMPLATE_MIDDLE = new byte[]{
            0x15, 0x00, 0x30, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00
    };
    private static final byte[] TEMPLATE_END = new byte[]{
            0x15, (byte) 0x80, 0x30, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00
    };

    private final HidDeviceCommunicator hidDeviceCommunicator;

    @Autowired
    public CX300Display(HidDeviceCommunicator hidDeviceCommunicator) {
        this.hidDeviceCommunicator = hidDeviceCommunicator;
    }

    public void setText(String top, String bottom) {
        if (top.length() > 28 || bottom.length() > 28) {
            throw new IllegalArgumentException();
        }

        String topPadded = String.format("%-28s", top);
        String bottomPadded = String.format("%-28s", bottom);

        hidDeviceCommunicator.sendMessage(INITIALISE_FULL);

        hidDeviceCommunicator.sendMessage(DISPLAY_TOP);
        hidDeviceCommunicator.sendMessage(createMiddleMessage(topPadded.substring(0, 8)));
        hidDeviceCommunicator.sendMessage(createMiddleMessage(topPadded.substring(8, 16)));
        hidDeviceCommunicator.sendMessage(createMiddleMessage(topPadded.substring(16, 24)));
        hidDeviceCommunicator.sendMessage(createEndMessage(topPadded.substring(24, 28)));

        hidDeviceCommunicator.sendMessage(DISPLAY_BOTTOM);
        hidDeviceCommunicator.sendMessage(createMiddleMessage(bottomPadded.substring(0, 8)));
        hidDeviceCommunicator.sendMessage(createMiddleMessage(bottomPadded.substring(8, 16)));
        hidDeviceCommunicator.sendMessage(createMiddleMessage(bottomPadded.substring(16, 24)));
        hidDeviceCommunicator.sendMessage(createEndMessage(bottomPadded.substring(24, 28)));
    }

    public void setText(String topLeft, String bottomLeft, String topRight, String bottomRight) {
        if (topLeft.length() > 16 || bottomLeft.length() > 16 || topRight.length() > 16 || bottomRight.length() > 16) {
            throw new IllegalArgumentException();
        }

        String topLeftPadded = String.format("%-16s", topLeft);
        String bottomLeftPadded = String.format("%-16s", bottomLeft);
        String topRightPadded = String.format("%16s", topRight);
        String bottomRightPadded = String.format("%16s", bottomRight);

        hidDeviceCommunicator.sendMessage(INITIALISE_CORNERS);

        hidDeviceCommunicator.sendMessage(DISPLAY_TOP_LEFT);
        hidDeviceCommunicator.sendMessage(createMiddleMessage(topLeftPadded.substring(0, 8)));
        hidDeviceCommunicator.sendMessage(createEndMessage(topLeftPadded.substring(8, 16)));

        hidDeviceCommunicator.sendMessage(DISPLAY_BOTTOM_LEFT);
        hidDeviceCommunicator.sendMessage(createMiddleMessage(bottomLeftPadded.substring(0, 8)));
        hidDeviceCommunicator.sendMessage(createEndMessage(bottomLeftPadded.substring(8, 16)));

        hidDeviceCommunicator.sendMessage(DISPLAY_TOP_RIGHT);
        hidDeviceCommunicator.sendMessage(createMiddleMessage(topRightPadded.substring(0, 8)));
        hidDeviceCommunicator.sendMessage(createEndMessage(topRightPadded.substring(8, 16)));

        hidDeviceCommunicator.sendMessage(DISPLAY_BOTTOM_RIGHT);
        hidDeviceCommunicator.sendMessage(createMiddleMessage(bottomRightPadded.substring(0, 8)));
        hidDeviceCommunicator.sendMessage(createEndMessage(bottomRightPadded.substring(8, 16)));
    }

    public void clear() {
        hidDeviceCommunicator.sendMessage(DISPLAY_CLEAR);
    }

    private byte[] createMiddleMessage(String input) {
        byte[] message = Arrays.copyOf(TEMPLATE_MIDDLE, TEMPLATE_MIDDLE.length);
        return createMessage(input, message);
    }

    private byte[] createEndMessage(String input) {
        byte[] message = Arrays.copyOf(TEMPLATE_END, TEMPLATE_END.length);
        return createMessage(input, message);
    }

    private byte[] createMessage(String input, byte[] template) {
        if (input.length() <= 8) {
            byte[] inputBytes = input.getBytes();
            byte[] spacedBytes = new byte[inputBytes.length * 2];
            for (int i = 0; i < inputBytes.length; i++) {
                spacedBytes[i * 2] = inputBytes[i];
                spacedBytes[(i * 2) + 1] = 0x00;
            }

            System.arraycopy(spacedBytes, 0, template, 2, spacedBytes.length);
        }

        return template;
    }
}
