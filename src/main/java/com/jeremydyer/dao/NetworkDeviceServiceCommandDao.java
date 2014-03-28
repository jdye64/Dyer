package com.jeremydyer.dao;

import com.jeremydyer.core.NetworkDeviceServiceCommand;
import com.makeandbuild.persistence.jdbc.BaseDao;

import java.util.List;

/**
 * Created by jeremydyer on 3/27/14.
 */
public interface NetworkDeviceServiceCommandDao
    extends BaseDao<NetworkDeviceServiceCommand, Long> {

    List<NetworkDeviceServiceCommand> commandsForService(Long serviceId);

}
