package com.jeremydyer.core;

import java.io.Serializable;

/**
 * User: Jeremy Dyer
 * Date: 3/26/14
 * Time: 10:53 AM
 */
public class NetworkDevice
    implements Serializable {

    private Long networkDeviceId;
    private Long networkLocationId;
    private String internalIpAddress;
    private String os;
    private String description;

    public Long getNetworkDeviceId() {
        return networkDeviceId;
    }

    public void setNetworkDeviceId(Long networkDeviceId) {
        this.networkDeviceId = networkDeviceId;
    }

    public Long getNetworkLocationId() {
        return networkLocationId;
    }

    public void setNetworkLocationId(Long networkLocationId) {
        this.networkLocationId = networkLocationId;
    }

    public String getInternalIpAddress() {
        return internalIpAddress;
    }

    public void setInternalIpAddress(String internalIpAddress) {
        this.internalIpAddress = internalIpAddress;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
