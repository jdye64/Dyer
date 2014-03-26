package com.jeremydyer.core;

import com.jeremydyer.core.enums.DyerServiceEnum;

import java.io.Serializable;

/**
 * Defines a custom service that is running on the Network Device. This is not meant to hold the linux level services
 * running on the device but rather the custom services that I have written like, GPIO, camera software, etc.
 *
 * User: Jeremy Dyer
 * Date: 3/26/14
 * Time: 11:00 AM
 */
public class NetworkDeviceService
    implements Serializable {

    private Long networkDeviceServiceId;
    private Long networkDeviceId;
    private DyerServiceEnum serviceType;
    private int servicePort;

    public Long getNetworkDeviceServiceId() {
        return networkDeviceServiceId;
    }

    public void setNetworkDeviceServiceId(Long networkDeviceServiceId) {
        this.networkDeviceServiceId = networkDeviceServiceId;
    }

    public Long getNetworkDeviceId() {
        return networkDeviceId;
    }

    public void setNetworkDeviceId(Long networkDeviceId) {
        this.networkDeviceId = networkDeviceId;
    }

    public DyerServiceEnum getServiceType() {
        return serviceType;
    }

    public void setServiceType(DyerServiceEnum serviceType) {
        this.serviceType = serviceType;
    }

    public int getServicePort() {
        return servicePort;
    }

    public void setServicePort(int servicePort) {
        this.servicePort = servicePort;
    }
}
