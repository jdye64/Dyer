package com.jeremydyer.core;

import java.io.Serializable;
import java.util.List;

/**
 * Defines a network that devices are connected to. This is important if you desire to access devices for
 * example at a vacation house as well as your permanent residence.
 *
 * User: Jeremy Dyer
 * Date: 3/26/14
 * Time: 10:05 AM
 */
public class NetworkLocation
    implements Serializable {

    private Long networkLocationId;
    private String description;
    private String publicIpAddress;
    private String publicDns;

    public Long getNetworkLocationId() {
        return networkLocationId;
    }

    public void setNetworkLocationId(Long networkLocationId) {
        this.networkLocationId = networkLocationId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublicIpAddress() {
        return publicIpAddress;
    }

    public void setPublicIpAddress(String publicIpAddress) {
        this.publicIpAddress = publicIpAddress;
    }

    public String getPublicDns() {
        return publicDns;
    }

    public void setPublicDns(String publicDns) {
        this.publicDns = publicDns;
    }

}
