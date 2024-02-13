package com.github.passdrive.usbDetector.manager.platform;

import com.github.passdrive.usbDetector.manager.DetectTaskSchduler;
import com.github.passdrive.usbDetector.manager.Task;
import com.github.passdrive.usbDetector.platform.WindowsUsbDetector;

// WIN Task
public class WinTask extends Task {
    DetectTaskSchduler scheduler;

    public WinTask(DetectTaskSchduler scheduler) {
        this.scheduler = scheduler;
    }

    @Override
    public void run() {
        WindowsUsbDetector detector = new WindowsUsbDetector();
        try {
            if (detector.detect()) {
                System.out.println("USB Detected: " + detector.getDetectedDevice());
                scheduler.stop();
            } else {
                System.out.println("Waiting for USB device...");
            }
        } catch (InterruptedException e) {
            // TODO: fallback
            System.out.println("Try again Later!");
            System.exit(1);
        }
    }
}