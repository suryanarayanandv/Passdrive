package com.github.passdrive.protector.configProtection;

import java.io.File;

import com.github.passdrive.Environment.Environment;
import com.github.passdrive.Environment.EnvironmentImpl;
import com.github.passdrive.usbDetector.UsbDevice;

/**
 * If already passkey pendrive
 * -> true
 * else
 * -> false
 */

public class detectConfiguration {
    public static Boolean detect(UsbDevice usbDrive, Environment environment) {
        if (usbDrive.getIsDetected()) {
            EnvironmentImpl env = new EnvironmentImpl();
            /**
             * 
             * volume/.protected file will be generated if
             * it already been attached and configured
             * else create one -> configurePendrive
             * 
             */
            String root = "";
            env.getEnvironmentMap("root");

            /**
             * Can change Root path of .protected
             */

            File protect = new File(
                    "" + usbDrive.getDeviceVolume() + File.separator + root + File.separator + ".protected");
            if (protect.exists()) {
                return true;
            }
        }
        return false;
    }
}
