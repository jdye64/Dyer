package com.jeremydyer.core.network;

import com.makeandbuild.persistence.jdbc.SaveWhen;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Defines a network that devices are connected to. This is important if you desire to access devices for
 * example at a vacation house as well as your permanent residence.
 *
 * User: Jeremy Dyer
 * Date: 3/26/14
 * Time: 10:05 AM
 */
@Table(name = "network_location")
//@Specialize(typeColumn = "javatype")
public class NetworkLocation
    implements Serializable {

    @Id
    @Column(name = "network_location_id")
    @SaveWhen(insert = true, update = false)
    private Long networkLocationId;

    @Column(name = "desc")
    @SaveWhen(insert = true, update = false)
    private String description;

    @Column(name = "mobile_desc")
    @SaveWhen(insert = true, update = false)
    private String mobileDescription;

    @Column(name = "public_ip")
    @SaveWhen(insert = true, update = false)
    private String publicIpAddress;

    @Column(name = "public_dns")
    @SaveWhen(insert = true, update = false)
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

    public String getMobileDescription() {
        return mobileDescription;
    }

    public void setMobileDescription(String mobileDescription) {
        this.mobileDescription = mobileDescription;
    }
}
