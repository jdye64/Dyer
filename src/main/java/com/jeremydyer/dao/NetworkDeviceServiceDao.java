package com.jeremydyer.dao;

import com.jeremydyer.core.NetworkDeviceService;
import com.makeandbuild.persistence.jdbc.BaseDao;

import java.util.List;

/**
 * Created by jeremydyer on 3/27/14.
 */
public interface NetworkDeviceServiceDao
        extends BaseDao<NetworkDeviceService, Long> {

    List<NetworkDeviceService> servicesForDevice(Long deviceId);
}
