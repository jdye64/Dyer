package com.jeremydyer.core;

import com.makeandbuild.persistence.jdbc.SaveWhen;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * User: Jeremy Dyer
 * Date: 3/26/14
 * Time: 10:53 AM
 */
@Table(name = "location_device")
//@Specialize(typeColumn = "javatype")
public class NetworkDevice
    implements Serializable {

    @Id
    @Column(name = "location_device_id")
    @SaveWhen(insert = true, update = false)
    private Long networkDeviceId;

    @Column(name = "network_location_id")
    @SaveWhen(insert = true, update = true)
    private Long networkLocationId;

    @Column(name = "internal_ip")
    @SaveWhen(insert = true, update = true)
    private String internalIpAddress;

    @Column(name = "os")
    @SaveWhen(insert = true, update = true)
    private String os;

    @Column(name = "desc")
    @SaveWhen(insert = true, update = true)
    private String description;

    @Column(name = "mobile_desc")
    @SaveWhen(insert = true, update = true)
    private String mobileDescription;

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

    public String getMobileDescription() {
        return mobileDescription;
    }

    public void setMobileDescription(String mobileDescription) {
        this.mobileDescription = mobileDescription;
    }
}
