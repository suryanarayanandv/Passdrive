package com.github.passdrive.usbDetector;

public class UsbDevice {
    private Boolean isDetected;
    private String deviceVolume;

    public UsbDevice(Boolean isDetected, String deviceVolume) {
        this.isDetected = isDetected;
        this.deviceVolume = deviceVolume;
    }

    public String getDeviceVolume() {
        return deviceVolume;
    }

    public Boolean getIsDetected() {
        return isDetected;
    }

    public void setDeviceVolume(String deviceVolume) {
        this.deviceVolume = deviceVolume;
    }

    public void setIsDetected(Boolean isDetected) {
        this.isDetected = isDetected;
    }
}
