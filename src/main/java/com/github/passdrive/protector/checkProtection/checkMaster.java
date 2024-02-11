package com.github.passdrive.protector.checkProtection;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import com.github.passdrive.Environment.Environment;
import com.github.passdrive.Environment.EnvironmentImpl;
import com.github.passdrive.usbDetector.UsbDevice;

/**
 * Check if entered password matches
 * stored encrypted password
 * Store Env: status=?(bool)
 */

public class checkMaster {
    public static Boolean check(UsbDevice usbDrive, String hashedPassword, Environment environment) {
        if (usbDrive.getIsDetected()) {
            EnvironmentImpl env = new EnvironmentImpl();
            /**
             * 
             * If master password matches -> Env: status=true
             * Else -> Env: status=false
             * 
             */
            String root = "";
            env.getEnvironmentMap("root");

            File protect = new File(
                    "" + usbDrive.getDeviceVolume() + File.separator + root + File.separator + ".protected");

            try {
                protect.createNewFile();
                FileInputStream fin = new FileInputStream(protect);

                if (fin.readAllBytes().toString().equals(hashedPassword)) {
                    environment.setEnvironmentMap("status", "true");
                    fin.close();
                    return true;
                } else {
                    environment.setEnvironmentMap("status", "false");
                }
                fin.close();
                return true;
            } catch (IOException io) {
                // Fallback
                return false;
            }
        }
        return false;
    }
}
