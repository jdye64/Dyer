package com.jeremydyer.service.network;

import com.jeremydyer.core.network.dto.NetworkServiceCommandResponseDTO;

/**
 * Service for issuing commands to Services running on a NetworkDevice.
 *
 * Created by jeremydyer on 3/27/14.
 */
public interface CommandService {

    public NetworkServiceCommandResponseDTO executeServiceCommandOnDevice(Long serviceId, Long commandId, Long deviceId);
}
