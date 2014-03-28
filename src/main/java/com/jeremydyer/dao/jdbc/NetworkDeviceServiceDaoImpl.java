package com.jeremydyer.dao.jdbc;

import com.jeremydyer.core.NetworkDeviceService;
import com.jeremydyer.dao.NetworkDeviceServiceDao;
import com.makeandbuild.persistence.jdbc.BaseDaoImpl;
import com.makeandbuild.persistence.jdbc.ReflectionBasedJdbcMapper;

import java.util.List;

/**
 * Created by jeremydyer on 3/27/14.
 */
public class NetworkDeviceServiceDaoImpl
        extends BaseDaoImpl<NetworkDeviceService, Long>
        implements NetworkDeviceServiceDao {

    public NetworkDeviceServiceDaoImpl() {
        super(ReflectionBasedJdbcMapper.proxy(NetworkDeviceService.class), NetworkDeviceService.class, Long.class);
    }

    @Override
    public List<NetworkDeviceService> servicesForDevice(Long deviceId) {
        String sql = "SELECT * FROM " + this.getDomainMapper().getTablename() + " WHERE service_id IN(SELECT service_id FROM device_service WHERE device_id = ?)";
        return getJdbcTemplate().query(sql, new Object[]{deviceId}, getDomainMapper());
    }
}
