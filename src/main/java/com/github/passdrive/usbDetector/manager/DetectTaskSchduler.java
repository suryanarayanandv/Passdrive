package com.github.passdrive.usbDetector.manager;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.github.passdrive.Environment.Environment;
import com.github.passdrive.usbDetector.manager.platform.LinTask;
import com.github.passdrive.usbDetector.manager.platform.WinTask;

/**
 * Thread-Safe Task Scheduler
 * for detecting USB devices
 */
public class DetectTaskSchduler {
    private String detectedDevice;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    
    synchronized public void start(Environment environment) {

        // Get os
        String os = (String) environment.getEnvironmentMap("platform");

        if ( os != null ) {
            if ( os.equals("win32") ) {
                // Schedule the task to run every 5 seconds
                scheduler.scheduleAtFixedRate(new WinTask(this), 0, 5, TimeUnit.SECONDS);
            } else if ( os.equals("linux") ) {
                // Schedule the task to run every 5 seconds
                scheduler.scheduleAtFixedRate(new LinTask(this), 0, 5, TimeUnit.SECONDS);
            } else if ( os.equals("darwin") ) {
                // Schedule the task to run every 5 seconds
                scheduler.scheduleAtFixedRate(new LinTask(this), 0, 5, TimeUnit.SECONDS);
            }
        }
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
