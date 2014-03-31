package com.jeremydyer.dao.network;

import com.jeremydyer.core.network.NetworkDeviceServiceCommand;
import com.makeandbuild.persistence.jdbc.BaseDao;

import java.util.List;

/**
 * Created by jeremydyer on 3/27/14.
 */
public interface NetworkDeviceServiceCommandDao
    extends BaseDao<NetworkDeviceServiceCommand, Long> {

    List<NetworkDeviceServiceCommand> commandsForService(Long serviceId);

}
