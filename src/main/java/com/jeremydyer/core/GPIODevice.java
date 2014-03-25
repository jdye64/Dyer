package com.jeremydyer.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jeremydyer.resource.GPIOResource;
import org.apache.log4j.Logger;

import java.io.Serializable;

/**
 * User: Jeremy Dyer
 * Date: 3/25/14
 * Time: 2:54 PM
 */
public class GPIODevice
    implements Serializable {

    private static final Logger logger = Logger.getLogger(GPIOResource.class);

    @JsonProperty
    private String ipaddress;

    public GPIODevice() {}

    public String getIpaddress() {
        return ipaddress;
    }

    public void setIpaddress(String ipaddress) {
        this.ipaddress = ipaddress;
    }
}
