package com.jeremydyer.service.impl;

import com.jeremydyer.core.NetworkDeviceServiceCommand;
import com.jeremydyer.core.NetworkDevice;
import com.jeremydyer.core.NetworkDeviceService;
import com.jeremydyer.core.NetworkLocation;
import com.jeremydyer.core.dto.NetworkServiceCommandResponse;
import com.jeremydyer.core.enums.DyerServiceEnum;
import com.jeremydyer.service.GPIOService;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.HttpMethod;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Service layer for GPIO implementation
 *
 * User: Jeremy Dyer
 * Date: 3/26/14
 * Time: 8:55 AM
 */
public class GPIOServiceInMemory
    implements GPIOService {

    private static final Logger logger = LoggerFactory.getLogger(GPIOServiceInMemory.class);

    private List<NetworkLocation> locations = new ArrayList<NetworkLocation>();
    private List<NetworkDevice> devices = new ArrayList<NetworkDevice>();
    private List<NetworkDeviceService> services = new ArrayList<NetworkDeviceService>();
    private List<NetworkDeviceServiceCommand> commands = new ArrayList<NetworkDeviceServiceCommand>();


    public GPIOServiceInMemory() {
        this.crazyLazySetupMethodToNotHaveToUseDatabase();
    }


    @Override
    public List<NetworkLocation> retrieveNetworkLocationsForUser(Long networkLocationId, Long userId) {
        if (networkLocationId != null) {
            //Only return a single network location.
            for (NetworkLocation loc : locations) {
                if (loc.getNetworkLocationId().equals(networkLocationId)) {
                    return new ArrayList<NetworkLocation>(Arrays.asList(loc));
                }
            }
            return null;
        } else {
            //Just return all locations.
            return locations;
        }
    }


    @Override
    public List<NetworkDevice> retrieveNetworkDeviceForUserAtLocation(Long networkDeviceId, Long locationId,
                                                                      Long userId) {
        if (networkDeviceId != null) {
            //Look for a particular device
            for (NetworkDevice dev : devices) {
                if (dev.getNetworkDeviceId().equals(networkDeviceId)) {
                    return new ArrayList<NetworkDevice>(Arrays.asList(dev));
                }
            }
            return null;
        } else {
            //Return all devices at a location.
            List<NetworkDevice> devs = new ArrayList<NetworkDevice>();
            for (NetworkDevice dev : devices) {
                if (dev.getNetworkLocationId().equals(locationId)) {
                    devs.add(dev);
                }
            }
            return devs;
        }
    }


    @Override
    public List<NetworkDeviceService> retrieveNetworkDeviceServiceForDeviceAndUser(Long networkDeviceServiceId, Long networkDeviceId, Long UserId) {

        if (networkDeviceServiceId != null) {
            //Look for a particular service
            for (NetworkDeviceService service : services) {
                if (service.getNetworkDeviceServiceId().equals(networkDeviceServiceId)) {
                    return new ArrayList<NetworkDeviceService>(Arrays.asList(service));
                }
            }
            return null;
        } else {
            //Return all devices at a location.
            List<NetworkDeviceService> deviceServices = new ArrayList<NetworkDeviceService>();
            for (NetworkDeviceService dev : services) {
                if (dev.getNetworkDeviceId().equals(networkDeviceId)) {
                    deviceServices.add(dev);
                }
            }
            return deviceServices;
        }
    }


    @Override
    public List<NetworkDeviceServiceCommand> retrieveDeviceCommandsForDeviceServiceOnDeviceWithUser(Long serviceCommandId,
                                                                                             Long networkDeviceServiceId, Long networkDeviceId, Long userId) {

        if (serviceCommandId != null) {
            //Return a specific command
            for (NetworkDeviceServiceCommand command : commands) {
                if (command.getDeviceCommandId().equals(serviceCommandId)) {
                    return new ArrayList<NetworkDeviceServiceCommand>(Arrays.asList(command));
                }
            }
            return null;
        } else {
            //Return all of the commands for a particular service on a particular device.
            List<NetworkDeviceServiceCommand> serviceCommands = new ArrayList<NetworkDeviceServiceCommand>();
            for (NetworkDeviceServiceCommand command : commands) {
                if (command.getNetworkDeviceServiceId().equals(networkDeviceServiceId)) {
                    serviceCommands.add(command);
                }
            }
            return serviceCommands;
        }
    }


    @Override
    public NetworkServiceCommandResponse executeServiceCommandWithParameters(Long serviceCommandId, Long serviceId,
                                                                             Long deviceId, Long locationId,
                                                                             Long userId,
                                                                             Map<String, String> commandParameters) {

        List<NetworkDeviceServiceCommand> commandsLocal = retrieveDeviceCommandsForDeviceServiceOnDeviceWithUser
                (serviceCommandId, serviceId, deviceId, userId);

        List<NetworkDeviceService> servicesLocal = retrieveNetworkDeviceServiceForDeviceAndUser(serviceId, deviceId,
                userId);

        List<NetworkDevice> devicesLocal = retrieveNetworkDeviceForUserAtLocation(deviceId, locationId, userId);

        if (commandsLocal != null && commandsLocal.size() == 1) {
            NetworkDeviceServiceCommand command = commandsLocal.get(0);
            NetworkDeviceService service = servicesLocal.get(0);
            NetworkDevice device = devicesLocal.get(0);

            logger.info("Running command " + command.getCommandName());
            logger.info("Command URI -> " + command.getServiceUri() + " : " + command.getHttpMethod());
            logger.info("Attempting to run command on device " + device.getInternalIpAddress());

            //Gets the command Device so that devices IP address can be retrieved
            Client client = Client.create();
            client.setConnectTimeout(10000);
            String url = "http://" + device.getInternalIpAddress() + ":" + service
                    .getServicePort() + command.getServiceUri();
            logger.info("Command URL -> '" + url + "'");
            WebResource webResource = client.resource(url);

            ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
            String output = response.getEntity(String.class);
            logger.info("Command Response -> " + output);

            NetworkServiceCommandResponse res = new NetworkServiceCommandResponse();
            res.setResponse(output);

            return res;
        } else {
            logger.error("Crap we can't find the network service command that you are trying to run are you sure you " +
                    "have that setup?");
            return new NetworkServiceCommandResponse();
        }
    }


//    public void consumeDevicePostBack(GPIODevicePostBack postBack) {
//        logger.info("Consuming PostBack from Device with IP address: " + postBack.getIpaddress());
//
//        //        //Makes sure that the device is not already in the list
////        boolean found = false;
////        for (GPIODevice dev : devices) {
////            if (dev.getIpaddress().equals(device.getIpaddress())) {
////                found = true;
////                break;
////            }
////        }
////
////        if (!found) {
////            devices.add(device);
////            System.out.println("There are now " + devices.size() + " GPIODevices in memory list");
////        }
//    }



    private void crazyLazySetupMethodToNotHaveToUseDatabase() {

        logger.info("BUILDING INFORMATION!!!");

        //Create the network locations.
        NetworkLocation apartment = new NetworkLocation();
        apartment.setNetworkLocationId(1L);
        apartment.setDescription("Dyer Virginia Highlands Apartment");
        apartment.setPublicIpAddress("76.20.248.249");
        apartment.setPublicDns("www.jeremydyer.me");
        locations.add(apartment);

        //Creates the NetworkDevice(s) and their services.
        NetworkDevice bedroom = new NetworkDevice();
        bedroom.setNetworkDeviceId(1L);
        bedroom.setNetworkLocationId(apartment.getNetworkLocationId());
        bedroom.setOs("Raspbian 3.5.4");
        bedroom.setDescription("Bedroom PowerSwitch");
        bedroom.setShortDescription("Bed");
        bedroom.setInternalIpAddress("10.0.1.50");
        devices.add(bedroom);

        NetworkDevice jeremyNightStand = new NetworkDevice();
        jeremyNightStand.setNetworkDeviceId(2L);
        jeremyNightStand.setNetworkLocationId(apartment.getNetworkLocationId());
        jeremyNightStand.setOs("Raspbian 3.5.4");
        jeremyNightStand.setDescription("Jeremy Nightstand PowerSwitch");
        jeremyNightStand.setShortDescription("J-Nite");
        jeremyNightStand.setInternalIpAddress("10.0.1.51");
        devices.add(jeremyNightStand);

        NetworkDevice carlaNightStand = new NetworkDevice();
        carlaNightStand.setNetworkDeviceId(3L);
        carlaNightStand.setNetworkLocationId(apartment.getNetworkLocationId());
        carlaNightStand.setOs("Raspbian 3.5.4");
        carlaNightStand.setDescription("Carla Nightstand PowerSwitch");
        carlaNightStand.setShortDescription("C-Nite");
        carlaNightStand.setInternalIpAddress("10.0.1.52");
        devices.add(carlaNightStand);


        //Creates the NetworkDeviceService(s) for the devices
        createServiceForDevice(bedroom, DyerServiceEnum.GPIO_SERVICE);
        createServiceForDevice(jeremyNightStand, DyerServiceEnum.GPIO_SERVICE);
        createServiceForDevice(carlaNightStand, DyerServiceEnum.GPIO_SERVICE);

    }


    private void createServiceForDevice(NetworkDevice device, DyerServiceEnum serviceEnum) {
        NetworkDeviceService service = new NetworkDeviceService();
        service.setNetworkDeviceId(device.getNetworkDeviceId());
        service.setNetworkDeviceServiceId(new Long(services.size() + 1));
        service.setServiceType(serviceEnum);
        service.setServicePort(5000);
        services.add(service);

        //Creates the commands for the service that was just created.
        buildGPIOServiceCommandsForService(service);
    }

    private void buildGPIOServiceCommandsForService(NetworkDeviceService service) {
        //Creates the GPIO commands for the NetworkDeviceService.
        NetworkDeviceServiceCommand command = new NetworkDeviceServiceCommand();
        command.setDeviceCommandId(new Long(commands.size() + 1));
        command.setNetworkDeviceServiceId(service.getNetworkDeviceServiceId());
        command.setHttpMethod(HttpMethod.GET);
        command.setServiceUri("/gpio/api/v1.0/");
        command.setCommandName("gpio_state");
        command.setCommandDescription("Gets the current state of all of the GPIO channels");
        commands.add(command);

        command = new NetworkDeviceServiceCommand();
        command.setDeviceCommandId(new Long(commands.size() + 1));
        command.setNetworkDeviceServiceId(service.getNetworkDeviceServiceId());
        command.setHttpMethod(HttpMethod.GET);
        command.setServiceUri("/gpio/api/v1.0/<int:channel_id>");
        command.setCommandName("gpio_channel_state");
        command.setCommandDescription("Gets the current state of an individual channel");
        commands.add(command);

        command = new NetworkDeviceServiceCommand();
        command.setDeviceCommandId(new Long(commands.size() + 1));
        command.setNetworkDeviceServiceId(service.getNetworkDeviceServiceId());
        command.setHttpMethod(HttpMethod.POST);
        command.setServiceUri("/gpio/api/v1.0/<int:channel_id>");
        command.setCommandName("gpio_set_channel_value");
        command.setCommandDescription("Sets an individual GPIO channel value");
        commands.add(command);

    }
}
