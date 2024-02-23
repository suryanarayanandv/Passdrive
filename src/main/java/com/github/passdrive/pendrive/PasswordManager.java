package com.github.passdrive.pendrive;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import com.github.passdrive.Environment.EnvironmentImpl;
import com.github.passdrive.encryptor.Encipher;
import com.github.passdrive.encryptor.Algorithms.interfaces.Algorithm;
import com.github.passdrive.protector.hashing.Hash;
import com.github.passdrive.protector.hashing.interfaces.hash;
import com.github.passdrive.usbDetector.UsbDevice;

/*
 * Manages stores encrypted and
 * hashed password
 * test if password is correct
 */

/*
 * env: "root"
 * Path(master): Volume/.protected
 * Path(passwords): Volume/data/passwords/domain
 */

public class PasswordManager {
    public static Boolean storeMasterPassword(UsbDevice usb, String password) {
        if ( usb.getIsDetected() ) {
            // Get Environment root to store config file
            String root = "";
            EnvironmentImpl.getEnvironmentMap("root");

            File protect = new File(
                    "" + usb.getDeviceVolume() + File.separator + root + File.separator + ".protected");
            
            // Create .protected write master password
            try {
                protect.createNewFile();
                FileOutputStream fio = new FileOutputStream(protect);

                hash sha = Hash.getAlgorithm("SHA-256");
                fio.write( sha.doHash(password).getBytes() );
                fio.close();

                return true;
            } catch (IOException io) {
                // Fallback
                return false;
            }
        }
        return false;
    }

    public static Boolean checkMasterPassword(UsbDevice usb, String password) {
        if ( usb.getIsDetected() ) {
            // Get Environment root to store config file
            String root = "";
            EnvironmentImpl.getEnvironmentMap("root");

            File protect = new File(
                    "" + usb.getDeviceVolume() + File.separator + root + File.separator + ".protected");
            
            try {
                if ( !protect.exists() ) {
                    return false;
                }

                // Read master password from .protected
                FileInputStream fio = new FileInputStream(protect);
                byte[] data = new byte[(int) protect.length()];
                fio.read(data);
                fio.close();

                hash sha = Hash.getAlgorithm("SHA-256");
                String hashedPassword = sha.doHash(password);
                return sha.checkHash(data.toString(), hashedPassword);
            } catch (IOException io) {
                // Fallback
                return false;
            }
        }
        return false;
    }

    // Site passwords
    public static Boolean storePassword(UsbDevice usb, String domainPath, String subDomain, String password) {
        if ( usb.getIsDetected() ) {
            // Get Environment root to store config file
            String root = "";
            EnvironmentImpl.getEnvironmentMap("root");

            File protect = new File(
                    "" + usb.getDeviceVolume() + File.separator + root + File.separator + ".protected");
            File passwords = new File(
                    "" + usb.getDeviceVolume() + File.separator + root + File.separator + "data");

            try {
                if ( !protect.exists() ) {
                    return false;
                }
                // Recursively create if not exists
                if ( !passwords.exists() ) {
                    passwords.mkdirs();
                }
                new File(passwords.getAbsolutePath() + File.separator + domainPath).createNewFile();
                FileOutputStream fio = new FileOutputStream(passwords.getAbsolutePath() + File.separator + domainPath);
                
                Algorithm aes = Encipher.getAlgorithm("AES");
                aes.init();
                fio.write( aes.encrypt(subDomain).getBytes() );
                fio.write( aes.encrypt(password).getBytes() );
                fio.close();
            } catch (Exception io) {
                // Fallback
                return false;
            }
        }
        return false;
    }

    public static String getPassword(UsbDevice usb, String domainPath, String subDomain) {
        if ( usb.getIsDetected() ) {
            // Get Environment root to store config file
            String root = "";
            EnvironmentImpl.getEnvironmentMap("root");

            File protect = new File(
                    "" + usb.getDeviceVolume() + File.separator + root + File.separator + ".protected");
            File passwords = new File(
                    "" + usb.getDeviceVolume() + File.separator + root + File.separator + "data");

            try {
                if ( !protect.exists() ) {
                    return null;
                }
                // Recursively create if not exists
                if ( !passwords.exists() ) {
                    return null;
                }
                File domain = new File(passwords.getAbsolutePath() + File.separator + domainPath);
                if ( !domain.exists() ) {
                    return null;
                }

                FileInputStream fio = new FileInputStream(domain);
                byte[] data = new byte[(int) domain.length()];
                fio.read(data);
                fio.close();

                Algorithm aes = Encipher.getAlgorithm("AES");
                aes.init();
                String decryptedData = aes.decrypt(data.toString());
                if ( decryptedData.split(":")[0].equals(subDomain) ) {
                    return decryptedData.split(":")[1];
                }
            } catch (Exception io) {
                // Fallback
                return null;
            }
        }
        return null;
    }

}
