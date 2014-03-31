package com.jeremydyer.core.network.dto;

import com.jeremydyer.core.network.NetworkDevice;
import com.jeremydyer.core.network.NetworkDeviceService;

import java.io.Serializable;
import java.util.List;

/**
 * Data Transfer object for the NetworkDevice object.
 *
 * User: Jeremy Dyer
 * Date: 3/27/14
 * Time: 12:09 PM
 */
public class NetworkDeviceDTO
    implements Serializable {

    private NetworkDevice networkDevice;
    private List<NetworkDeviceService> deviceServices;

    public NetworkDeviceDTO(NetworkDevice device, List<NetworkDeviceService> services) {
        this.networkDevice = device;
        this.deviceServices = services;
    }

    public NetworkDevice getNetworkDevice() {
        return networkDevice;
    }

    public void setNetworkDevice(NetworkDevice networkDevice) {
        this.networkDevice = networkDevice;
    }

    public List<NetworkDeviceService> getDeviceServices() {
        return deviceServices;
    }

    public void setDeviceServices(List<NetworkDeviceService> deviceServices) {
        this.deviceServices = deviceServices;
    }
}
