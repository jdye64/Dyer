package com.jeremydyer.dao.network.jdbc;

import com.jeremydyer.core.network.NetworkLocation;
import com.jeremydyer.dao.network.NetworkLocationDao;
import com.makeandbuild.persistence.jdbc.BaseDaoImpl;
import com.makeandbuild.persistence.jdbc.ReflectionBasedJdbcMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by jeremydyer on 3/27/14.
 */
public class NetworkLocationDaoImpl
        extends BaseDaoImpl<NetworkLocation, Long>
        implements NetworkLocationDao {

    private static final Logger logger = LoggerFactory.getLogger(NetworkLocationDaoImpl.class);

    public NetworkLocationDaoImpl() {
        super(ReflectionBasedJdbcMapper.proxy(NetworkLocation.class), NetworkLocation.class, Long.class);
    }

    @Override
    public List<NetworkLocation> networkLocationsForUser(Long userId) {
        String sql = "SELECT * FROM " + this.getDomainMapper().getTablename();
        return getJdbcTemplate().query(sql, new Object[]{}, getDomainMapper());
    }
}
