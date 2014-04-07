package com.jeremydyer.service.power.impl;

import com.jeremydyer.core.network.NetworkDevice;
import com.jeremydyer.core.power.PowerOutlet;
import com.jeremydyer.dao.network.NetworkDeviceDao;
import com.jeremydyer.service.network.NetworkService;
import com.jeremydyer.service.power.PowerService;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * User: Jeremy Dyer
 * Date: 4/4/14
 * Time: 11:00 AM
 */
public class PowerServiceImpl
    implements PowerService, NetworkService {

    private static final Logger logger = LoggerFactory.getLogger(PowerServiceImpl.class);

    @Autowired
    private NetworkDeviceDao networkDeviceDao;

    private Long servicePort;


    public PowerServiceImpl() {

        ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
        exec.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                logger.info("Running task that is suppose to run every 60 seconds");
            }
        }, 0, 60, TimeUnit.SECONDS);

    }


    @Override
    public String setRPiPin(PowerOutlet powerOutlet) {
        logger.info("About to physically call device.");

        NetworkDevice networkDevice = networkDeviceDao.find(powerOutlet.getNetworkDeviceId());
        if (networkDevice != null) {
            String url = "http://" + networkDevice.getInternalIpAddress() + ":" + this.servicePort + "/gpio/api/v1" +
                    ".0/" + powerOutlet.getGpioPin();
            logger.info("Invoking REST service " + url);

            try {
                Client client = Client.create();
                WebResource webResource = client.resource(url);
                ClientResponse response = webResource.accept("application/json")
                        .post(ClientResponse.class);

                if (response.getStatus() != 200) {
                    throw new RuntimeException("Failed : HTTP error code : "
                            + response.getStatus());
                }

                String output = response.getEntity(String.class);

                System.out.println("Output from Server .... \n");
                System.out.println(output);

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            logger.error("No networkdevice located with ID " + powerOutlet.getNetworkDeviceId());
        }

//        NetworkServiceCommandResponseDTO responseDTO = null;
//
//        NetworkDevice device = networkDeviceDao.find(deviceId);
//        NetworkDeviceService service = networkDeviceServiceDao.find(serviceId);
//        NetworkDeviceServiceCommand command = networkDeviceServiceCommandDao.find(commandId);
//
//        try {
//
//            String commandFullUrl = device.getInternalIpAddress() + ":" + service.getServicePort() + command
//                    .getCommandUri();
//            logger.info("Command Full URL -> '" + commandFullUrl + "'");
//
//            WebResource webResource = client.resource(commandFullUrl);
//            ClientResponse response = null;
//
//            if (command.getHttpMethod().equalsIgnoreCase("GET")) {
//                response = webResource.accept("application/json")
//                        .get(ClientResponse.class);
//            } else if (command.getHttpMethod().equalsIgnoreCase("POST")) {
//                response = webResource.accept("application/json")
//                        .post(ClientResponse.class);
//            } else if (command.getHttpMethod().equalsIgnoreCase("DELETE")) {
//                response = webResource.accept("application/json")
//                        .delete(ClientResponse.class);
//            } else if (command.getHttpMethod().equalsIgnoreCase("PUT")) {
//                response = webResource.accept("application/json")
//                        .put(ClientResponse.class);
//            } else {
//                logger.info("Unsupported HTTPMethod -> '" + command.getHttpMethod() + "'");
//            }
//
//
//            if (response.getStatus() != 200) {
//                throw new RuntimeException("Failed : HTTP error code : "
//                        + response.getStatus());
//            }
//
//            String output = response.getEntity(String.class);
//            logger.info("Output from Server .... \n");
//            logger.info(output);
//
//            responseDTO = new NetworkServiceCommandResponseDTO(device, service, command, output);
//
//        } catch (Exception e) {
//            logger.error("", e);
//            responseDTO = new NetworkServiceCommandResponseDTO(device, service, command, "");
//        }
//
//        return responseDTO;

        return "some stupid string";
    }

    @Override
    public void setNetworkServicePort(Long servicePort) {
        this.servicePort = servicePort;
    }
}
