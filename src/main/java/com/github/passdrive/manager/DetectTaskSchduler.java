package com.github.passdrive.manager;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.github.passdrive.detectors.WindowsUsbDetector;

/**
 * Thread-Safe Task Scheduler
 * for detecting USB devices
 */
public class DetectTaskSchduler {
    private String detectedDevice;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    
    synchronized public void start() {
        // Schedule the task to run every 5 seconds
        scheduler.scheduleAtFixedRate(new WinTask(this), 0, 5, TimeUnit.SECONDS);
    }

    synchronized public void stop() {
        scheduler.shutdown();
    }

    // For Scheduler Wrappers
    public String getDetectedDevice() {
        return detectedDevice;
    }

    public void setDetectedDevice(String detectedDevice) {
        this.detectedDevice = detectedDevice;
    }

}

// WIN Task
class WinTask extends Task {
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