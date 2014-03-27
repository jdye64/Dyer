package com.jeremydyer.service.impl;

import com.jeremydyer.core.NetworkDevice;
import com.jeremydyer.core.NetworkDeviceService;
import com.jeremydyer.core.NetworkLocation;
import com.jeremydyer.core.enums.DyerServiceEnum;
import com.jeremydyer.service.NetworkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * User: Jeremy Dyer
 * Date: 3/27/14
 * Time: 12:35 PM
 */
public class NetworkServiceInMemory
    implements NetworkService {

    private static final Logger logger = LoggerFactory.getLogger(GPIOServiceInMemory.class);

    private List<NetworkLocation> locations = new ArrayList<NetworkLocation>();
    private List<NetworkDevice> devices = new ArrayList<NetworkDevice>();
    private List<NetworkDeviceService> services = new ArrayList<NetworkDeviceService>();


    public NetworkServiceInMemory() {
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
    public List<NetworkDevice> retrieveNetworkDeviceForUserAtLocation(Long networkDeviceId, Long userId) {
        if (networkDeviceId != null) {
            //Look for a particular device
            for (NetworkDevice dev : devices) {
                if (dev.getNetworkDeviceId().equals(networkDeviceId)) {
                    return new ArrayList<NetworkDevice>(Arrays.asList(dev));
                }
            }
            return null;
        } else {
            //Return all devices.
            return this.devices;
        }
    }


    @Override
    public List<NetworkDeviceService> retrieveNetworkDeviceServiceForUser(Long networkDeviceServiceId, Long UserId) {

        if (networkDeviceServiceId != null) {
            //Look for a particular service
            for (NetworkDeviceService service : services) {
                if (service.getNetworkDeviceServiceId().equals(networkDeviceServiceId)) {
                    return new ArrayList<NetworkDeviceService>(Arrays.asList(service));
                }
            }
            return null;
        } else {
            //Return all services.
            return this.services;
        }
    }


    private void crazyLazySetupMethodToNotHaveToUseDatabase() {

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
        bedroom.setOs("Raspbian 1.5.4");
        bedroom.setDescription("Bedroom PowerSwitch");
        bedroom.setInternalIpAddress("10.0.1.50");
        devices.add(bedroom);

        NetworkDevice jeremyNightStand = new NetworkDevice();
        jeremyNightStand.setNetworkDeviceId(2L);
        jeremyNightStand.setNetworkLocationId(apartment.getNetworkLocationId());
        jeremyNightStand.setOs("Raspbian 1.5.4");
        jeremyNightStand.setDescription("Jeremy Nightstand PowerSwitch");
        jeremyNightStand.setInternalIpAddress("10.0.1.51");
        devices.add(jeremyNightStand);

        NetworkDevice carlaNightStand = new NetworkDevice();
        carlaNightStand.setNetworkDeviceId(3L);
        carlaNightStand.setNetworkLocationId(apartment.getNetworkLocationId());
        carlaNightStand.setOs("Raspbian 1.5.4");
        carlaNightStand.setDescription("Carla Nightstand PowerSwitch");
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
    }
}
