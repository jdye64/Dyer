package com.jeremydyer.dao;

import com.jeremydyer.core.NetworkDevice;
import com.makeandbuild.persistence.jdbc.BaseDao;

import java.util.List;

/**
 * Created by jeremydyer on 3/27/14.
 */
public interface NetworkDeviceDao
        extends BaseDao<NetworkDevice, Long> {

    List<NetworkDevice> devicesForUser(Long userId);

    List<NetworkDevice> networkDevicesAtLocation(Long networkLocationId);
}