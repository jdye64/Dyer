package com.jeremydyer.dao.network.jdbc;

import com.jeremydyer.core.network.NetworkDeviceServiceCommand;
import com.jeremydyer.dao.network.NetworkDeviceServiceCommandDao;
import com.makeandbuild.persistence.jdbc.BaseDaoImpl;
import com.makeandbuild.persistence.jdbc.ReflectionBasedJdbcMapper;

import java.util.List;

/**
 * Created by jeremydyer on 3/27/14.
 */
public class NetworkDeviceServiceCommandDaoImpl
        extends BaseDaoImpl<NetworkDeviceServiceCommand, Long>
        implements NetworkDeviceServiceCommandDao {

    public NetworkDeviceServiceCommandDaoImpl() {
        super(ReflectionBasedJdbcMapper.proxy(NetworkDeviceServiceCommand.class), NetworkDeviceServiceCommand.class, Long.class);
    }

    @Override
    public List<NetworkDeviceServiceCommand> commandsForService(Long serviceId) {
        String sql = "SELECT * FROM " + this.getDomainMapper().getTablename() + " WHERE device_service_id = ?";
        return getJdbcTemplate().query(sql, new Object[]{serviceId}, getDomainMapper());
    }
}