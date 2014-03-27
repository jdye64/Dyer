package com.jeremydyer.service;

import com.jeremydyer.core.NetworkDevice;
import com.jeremydyer.core.NetworkDeviceService;
import com.jeremydyer.core.NetworkLocation;

import java.util.List;

/**
 * User: Jeremy Dyer
 * Date: 3/27/14
 * Time: 12:35 PM
 */
public interface NetworkService {

    /**
     * Retrieves all of the NetworkLocations that a user is allowed to interact with.
     *
     * @param userId
     */
    public List<NetworkLocation> retrieveNetworkLocationsForUser(Long networkLocationId, Long userId);

    /**
     * Retrieves the NetworkDevices.
     *
     * @param networkDeviceId
     * @param userId
     * @return
     */
    public List<NetworkDevice> retrieveNetworkDeviceForUserAtLocation(Long networkDeviceId, Long userId);

    /**
     * Retrieves the NetworkDeviceServices.
     *
     * @param networkDeviceServiceId
     * @param UserId
     * @return
     */
    public List<NetworkDeviceService> retrieveNetworkDeviceServiceForUser(Long networkDeviceServiceId, Long UserId);
}
