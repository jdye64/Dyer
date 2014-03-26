package com.jeremydyer.core.gpio;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * User: Jeremy Dyer
 * Date: 3/26/14
 * Time: 8:59 AM
 */
public class GPIODevicePostBack
    implements Serializable {

    @JsonProperty
    private String ipaddress;

    public String getIpaddress() {
        return ipaddress;
    }

    public void setIpaddress(String ipaddress) {
        this.ipaddress = ipaddress;
    }
}
