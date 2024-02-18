package com.github.passdrive.usbDetector.PlatformDetectorTasks.interfaces;

/**
 * Usb Detector Interface
 * <windows>
 * <mac>
 * <linux>
 */

 public interface UsbDetector {
    Boolean detect() throws InterruptedException;
    String getDetectedDevice();
}