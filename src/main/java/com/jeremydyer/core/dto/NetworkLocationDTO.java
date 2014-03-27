package com.jeremydyer.core.dto;

import com.jeremydyer.core.NetworkDevice;
import com.jeremydyer.core.NetworkLocation;

import java.io.Serializable;
import java.util.List;

/**
 * Data transfer object for the NetworkLocation.
 *
 * User: Jeremy Dyer
 * Date: 3/27/14
 * Time: 11:38 AM
 */
public class NetworkLocationDTO
    implements Serializable {

    private NetworkLocation networkLocation;
    private List<NetworkDevice> locationDevices;

    public NetworkLocationDTO(NetworkLocation location, List<NetworkDevice> locationDevices) {
        this.networkLocation = location;
        this.locationDevices = locationDevices;
    }

    public NetworkLocation getNetworkLocation() {
        return networkLocation;
    }

    public void setNetworkLocation(NetworkLocation networkLocation) {
        this.networkLocation = networkLocation;
    }

    public List<NetworkDevice> getLocationDevices() {
        return locationDevices;
    }

    public void setLocationDevices(List<NetworkDevice> locationDevices) {
        this.locationDevices = locationDevices;
    }
}
