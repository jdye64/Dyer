package com.jeremydyer.service;

import com.jeremydyer.core.NetworkDeviceServiceCommand;
import com.jeremydyer.core.dto.NetworkServiceCommandResponseDTO;

import java.util.List;

/**
 * Service for issuing commands to Services running on a NetworkDevice.
 *
 * Created by jeremydyer on 3/27/14.
 */
public interface CommandService {

    public NetworkServiceCommandResponseDTO executeServiceCommandOnDevice(Long serviceId, Long commandId, Long deviceId);
}
