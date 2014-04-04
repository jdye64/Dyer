package com.jeremydyer.dao.power.jdbc;

import com.jeremydyer.core.power.PowerOutlet;
import com.jeremydyer.dao.power.PowerOutletDao;
import com.makeandbuild.persistence.jdbc.BaseDaoImpl;
import com.makeandbuild.persistence.jdbc.ReflectionBasedJdbcMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * User: Jeremy Dyer
 * Date: 4/4/14
 * Time: 10:45 AM
 */
public class PowerOutletDaoImpl
    extends BaseDaoImpl<PowerOutlet, Long>
    implements PowerOutletDao {

    private static final Logger logger = LoggerFactory.getLogger(PowerOutletDaoImpl.class);

    public PowerOutletDaoImpl() {
        super(ReflectionBasedJdbcMapper.proxy(PowerOutlet.class), PowerOutlet.class, Long.class);
    }
}
