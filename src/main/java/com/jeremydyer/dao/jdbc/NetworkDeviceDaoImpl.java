package com.jeremydyer.dao.jdbc;

import com.jeremydyer.core.NetworkDevice;
import com.jeremydyer.dao.NetworkDeviceDao;
import com.makeandbuild.persistence.jdbc.BaseDaoImpl;
import com.makeandbuild.persistence.jdbc.ReflectionBasedJdbcMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by jeremydyer on 3/27/14.
 */
public class NetworkDeviceDaoImpl
        extends BaseDaoImpl<NetworkDevice, Long>
        implements NetworkDeviceDao {

    private static final Logger logger = LoggerFactory.getLogger(NetworkDeviceDaoImpl.class);

    public NetworkDeviceDaoImpl() {
        super(ReflectionBasedJdbcMapper.proxy(NetworkDevice.class), NetworkDevice.class, Long.class);
    }

    @Override
    public List<NetworkDevice> devicesForUser(Long userId) {
        String sql = "SELECT * FROM " + this.getDomainMapper().getTablename();
        return getJdbcTemplate().query(sql, new Object[]{}, getDomainMapper());
    }

    @Override
    public List<NetworkDevice> networkDevicesAtLocation(Long networkLocationId) {
        String sql = "SELECT * FROM " + this.getDomainMapper().getTablename() + " WHERE network_location_id = ? ";
        return getJdbcTemplate().query(sql, new Object[]{networkLocationId}, getDomainMapper());
    }
}
