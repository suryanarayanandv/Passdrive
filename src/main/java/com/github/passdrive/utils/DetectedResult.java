package com.github.passdrive.utils;

public class DetectedResult {
    public Boolean isDetected;
    public String deviceVolume;

    public DetectedResult(Boolean isDetected, String deviceVolume) {
        this.isDetected = isDetected;
        this.deviceVolume = deviceVolume;
    }
}
