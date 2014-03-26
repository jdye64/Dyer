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
}
