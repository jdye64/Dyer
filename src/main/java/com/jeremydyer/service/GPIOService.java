package com.jeremydyer.service;

import com.jeremydyer.core.NetworkDeviceServiceCommand;
import com.jeremydyer.core.NetworkDevice;
import com.jeremydyer.core.NetworkDeviceService;
import com.jeremydyer.core.NetworkLocation;
import com.jeremydyer.core.dto.NetworkServiceCommandResponse;

import java.util.List;
import java.util.Map;

/**
 * User: Jeremy Dyer
 * Date: 3/26/14
 * Time: 10:44 AM
 */
public interface GPIOService {

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
     * @param locationId
     * @return
     */
    public List<NetworkDevice> retrieveNetworkDeviceForUserAtLocation(Long networkDeviceId, Long locationId,
                                                                      Long userId);

    /**
     * Retrieves the NetworkDeviceServices.
     *
     * @param networkDeviceServiceId
     * @param networkDeviceId
     * @param UserId
     * @return
     */
    public List<NetworkDeviceService> retrieveNetworkDeviceServiceForDeviceAndUser(Long networkDeviceServiceId, Long networkDeviceId, Long UserId);


    /**
     * Retrieves the commands for a particular service on a particular device.
     *
     * @param networkDeviceServiceId
     * @param networkDeviceId
     * @param userId
     * @return
     */
    public List<NetworkDeviceServiceCommand> retrieveDeviceCommandsForDeviceServiceOnDeviceWithUser(Long serviceCommandId,
                                                                                             Long networkDeviceServiceId,
                                                                                      Long networkDeviceId,
                                                                                      Long userId);

    /**
     * Executes a NetworkServiceCommand with the command parameters provided.
     *
     * @param commandParameters
     * @return
     */
    public NetworkServiceCommandResponse executeServiceCommandWithParameters(Long serviceCommandId,
                                                                             Long serviceId, Long deviceId,
                                                                             Long locationId,
                                                                             Long userId,
                                                                             Map<String, String> commandParameters);

//    public void consumeDevicePostBack(GPIODevicePostBack postBack);
}
