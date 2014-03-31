package com.jeremydyer.service.network.impl;

import com.jeremydyer.core.network.NetworkDevice;
import com.jeremydyer.core.network.NetworkDeviceService;
import com.jeremydyer.core.network.NetworkDeviceServiceCommand;
import com.jeremydyer.core.network.NetworkLocation;
import com.jeremydyer.dao.network.NetworkDeviceDao;
import com.jeremydyer.dao.network.NetworkDeviceServiceCommandDao;
import com.jeremydyer.dao.network.NetworkDeviceServiceDao;
import com.jeremydyer.dao.network.NetworkLocationDao;
import com.jeremydyer.service.network.NetworkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * NetworkService implementation that interacts with the DB for its data.
 *
 * Created by jeremydyer on 3/27/14.
 */
public class NetworkServiceImpl
    implements NetworkService {

    private static final Logger logger = LoggerFactory.getLogger(NetworkServiceImpl.class);

    @Autowired
    private NetworkLocationDao networkLocationDao;

    @Autowired
    private NetworkDeviceDao networkDeviceDao;

    @Autowired
    private NetworkDeviceServiceDao networkDeviceServiceDao;

    @Autowired
    private NetworkDeviceServiceCommandDao networkDeviceServiceCommandDao;


    @Override
    public NetworkLocation networkLocationById(Long networkLocationId) {
        return networkLocationDao.find(networkLocationId);
    }

    @Override
    public List<NetworkLocation> networkLocationsForUser(Long userId) {
        return networkLocationDao.networkLocationsForUser(userId);
    }

    @Override
    public NetworkDevice deviceById(Long networkDeviceId) {
        return networkDeviceDao.find(networkDeviceId);
    }

    @Override
    public List<NetworkDevice> allDevicesForUser(Long userId) {
        return networkDeviceDao.devicesForUser(userId);
    }

    @Override
    public List<NetworkDevice> devicesAtLocation(Long networkLocationId) {
        return networkDeviceDao.networkDevicesAtLocation(networkLocationId);
    }

    @Override
    public NetworkDeviceService serviceById(Long networkDeviceServiceId) {
        return networkDeviceServiceDao.find(networkDeviceServiceId);
    }

    @Override
    public List<NetworkDeviceService> servicesForDevice(Long deviceId) {
        return networkDeviceServiceDao.servicesForDevice(deviceId);
    }

    @Override
    public NetworkDeviceServiceCommand commandById(Long commandId) {
        return networkDeviceServiceCommandDao.find(commandId);
    }

    @Override
    public List<NetworkDeviceServiceCommand> commandsForService(Long serviceId) {
        return networkDeviceServiceCommandDao.commandsForService(serviceId);
    }
}
