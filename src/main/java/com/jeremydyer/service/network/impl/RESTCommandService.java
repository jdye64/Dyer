package com.jeremydyer.service.network.impl;

import com.jeremydyer.core.network.NetworkDevice;
import com.jeremydyer.core.network.NetworkDeviceService;
import com.jeremydyer.core.network.NetworkDeviceServiceCommand;
import com.jeremydyer.core.network.dto.NetworkServiceCommandResponseDTO;
import com.jeremydyer.dao.network.NetworkDeviceDao;
import com.jeremydyer.dao.network.NetworkDeviceServiceCommandDao;
import com.jeremydyer.dao.network.NetworkDeviceServiceDao;
import com.jeremydyer.service.network.CommandService;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
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
    private NetworkDeviceDao networkDeviceDao;

    @Autowired
    private NetworkDeviceServiceDao networkDeviceServiceDao;

    @Autowired
    private NetworkDeviceServiceCommandDao networkDeviceServiceCommandDao;

    private Client client = Client.create();

    @Override
    public NetworkServiceCommandResponseDTO executeServiceCommandOnDevice(Long serviceId, Long commandId, Long deviceId) {
        logger.info("About to physically call device.");

        NetworkServiceCommandResponseDTO responseDTO = null;

        NetworkDevice device = networkDeviceDao.find(deviceId);
        NetworkDeviceService service = networkDeviceServiceDao.find(serviceId);
        NetworkDeviceServiceCommand command = networkDeviceServiceCommandDao.find(commandId);

        try {

            String commandFullUrl = device.getInternalIpAddress() + ":" + service.getServicePort() + command
                    .getCommandUri();
            logger.info("Command Full URL -> '" + commandFullUrl + "'");

            WebResource webResource = client.resource(commandFullUrl);
            ClientResponse response = null;

            if (command.getHttpMethod().equalsIgnoreCase("GET")) {
                response = webResource.accept("application/json")
                        .get(ClientResponse.class);
            } else if (command.getHttpMethod().equalsIgnoreCase("POST")) {
                response = webResource.accept("application/json")
                        .post(ClientResponse.class);
            } else if (command.getHttpMethod().equalsIgnoreCase("DELETE")) {
                response = webResource.accept("application/json")
                        .delete(ClientResponse.class);
            } else if (command.getHttpMethod().equalsIgnoreCase("PUT")) {
                response = webResource.accept("application/json")
                        .put(ClientResponse.class);
            } else {
                logger.info("Unsupported HTTPMethod -> '" + command.getHttpMethod() + "'");
            }


            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatus());
            }

            String output = response.getEntity(String.class);
            logger.info("Output from Server .... \n");
            logger.info(output);

            responseDTO = new NetworkServiceCommandResponseDTO(device, service, command, output);

        } catch (Exception e) {
            logger.error("", e);
            responseDTO = new NetworkServiceCommandResponseDTO(device, service, command, "");
        }

        return responseDTO;
    }

}
