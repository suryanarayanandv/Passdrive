package com.github.passdrive.protector.checkProtection;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import com.github.passdrive.Environment.EnvironmentImpl;
import com.github.passdrive.usbDetector.UsbDevice;

/**
 * Check if entered password matches
 * stored encrypted password
 * Store Env: status=?(bool)
 */

public class checkMaster {
    public static Boolean check(UsbDevice usbDrive, String hashedPassword, EnvironmentImpl environment) {
        if (usbDrive.getIsDetected()) {
            /**
             * 
             * If master password matches -> Env: status=true
             * Else -> Env: status=false
             * 
             */
            String root = "";
            EnvironmentImpl.getEnvironmentMap("root");

            File protect = new File(
                    "" + usbDrive.getDeviceVolume() + File.separator + root + ".protected");

            try {
                if (!protect.exists()) {
                    return false;
                }
                FileInputStream fin = new FileInputStream(protect);
                ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                int nRead;
                byte[] data = new byte[16384];
                while ((nRead = fin.read(data, 0, data.length)) != -1) {
                    buffer.write(data, 0, nRead);
                }
                buffer.flush();
                byte[] byteArray = buffer.toByteArray();
                String password = new String(byteArray);
                if (password.equals(hashedPassword)) {
                    EnvironmentImpl.setEnvironmentMap("status", "true");
                    fin.close();
                    return true;
                } else {
                    EnvironmentImpl.setEnvironmentMap("status", "false");
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
