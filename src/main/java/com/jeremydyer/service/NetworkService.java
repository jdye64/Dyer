package com.jeremydyer.service;

import com.jeremydyer.core.NetworkDevice;
import com.jeremydyer.core.NetworkDeviceService;
import com.jeremydyer.core.NetworkDeviceServiceCommand;
import com.jeremydyer.core.NetworkLocation;

import java.util.List;

/**
 * User: Jeremy Dyer
 * Date: 3/27/14
 * Time: 12:35 PM
 */
public interface NetworkService {


    public NetworkLocation networkLocationById(Long networkLocationId);

    public List<NetworkLocation> networkLocationsForUser(Long userId);

    public NetworkDevice deviceById(Long networkDeviceId);

    public List<NetworkDevice> allDevicesForUser(Long userId);

    public List<NetworkDevice> devicesAtLocation(Long networkLocationId);

    public NetworkDeviceService serviceById(Long networkDeviceServiceId);

    public List<NetworkDeviceService> servicesForDevice(Long deviceId);

    public NetworkDeviceServiceCommand commandById(Long commandId);

    public List<NetworkDeviceServiceCommand> commandsForService(Long serviceId);
}
