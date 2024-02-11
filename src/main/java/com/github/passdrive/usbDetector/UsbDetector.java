package com.github.passdrive.usbDetector;

/**
 * Usb Detector Interface
 * <windows>
 * <mac>
 * <linux>
 */

public interface UsbDetector {
    Boolean detect();
    String getDetectedDevice();
}
