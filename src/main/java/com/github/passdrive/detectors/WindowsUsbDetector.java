package com.github.passdrive.detectors;

import static com.github.passdrive.utils.constants.UsbConstants.*;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.github.passdrive.utils.DetectedResult;

public class WindowsUsbDetector {
    private String detectedDevice;
    // Command
    private final String COMMAND = WMIC_PATH + " logicaldisk where drivetype=" + WIN_DEVICE_TPE
            + " get drivetype,deviceid /format:csv";

    private DetectedResult parseDevices(InputStream devices) {
        BufferedReader temp = new BufferedReader(new InputStreamReader(devices));
        String line;
        try {
            while ((line = temp.readLine()) != null) {
                // Skip the first line
                if (line.contains("Node,")) {
                    continue;
                }

                // Capture USB device
                else if (line.contains("" + WIN_DEVICE_TPE)) {
                    String[] device = line.split(",");
                    return new DetectedResult(true, device[1]);
                }
            }
        } catch (IOException e) {
            // TODO: fallback
            System.out.println("Try again Later!");
            System.exit(1);
        }
        return new DetectedResult(false, null);
    }

    public Boolean detect() throws InterruptedException {
        InputStream devices = null;
        DetectedResult result = null;
        // Detect USB
        System.out.println("Detecting USB");
        try {
            devices = Runtime.getRuntime().exec(COMMAND).getInputStream();
            if (devices != null) {
                result = parseDevices(new BufferedInputStream(devices));
            }

            // Return the detected USB
            if (result.isDetected) {
                detectedDevice = result.deviceVolume;
                return true;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getDetectedDevice() {
        return detectedDevice;
    }
}

