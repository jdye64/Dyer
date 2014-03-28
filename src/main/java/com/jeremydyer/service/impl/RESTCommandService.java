package com.jeremydyer.service.impl;

import com.jeremydyer.core.dto.NetworkServiceCommandResponseDTO;
import com.jeremydyer.dao.NetworkDeviceServiceCommandDao;
import com.jeremydyer.service.CommandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * REST implementation to invoke command via REST protocol.
 *
 * Created by jeremydyer on 3/27/14.
 */
public class RESTCommandService
    implements CommandService {

    private static final Logger logger = LoggerFactory.getLogger(RESTCommandService.class);

    @Autowired
    private NetworkDeviceServiceCommandDao networkDeviceServiceCommandDao;

    @Override
    public NetworkServiceCommandResponseDTO executeServiceCommandOnDevice(Long serviceId, Long commandId, Long deviceId) {
        logger.info("About to physically call device.");



        return new NetworkServiceCommandResponseDTO();
    }

}
