package com.jeremydyer.dao.woodshop.jdbc;

import com.jeremydyer.core.woodshop.WoodType;
import com.jeremydyer.dao.woodshop.WoodTypeDao;
import com.makeandbuild.persistence.jdbc.BaseDaoImpl;
import com.makeandbuild.persistence.jdbc.ReflectionBasedJdbcMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * User: Jeremy Dyer
 * Date: 3/31/14
 * Time: 12:45 PM
 */
public class WoodTypeDaoImpl
        extends BaseDaoImpl<WoodType, Long>
        implements WoodTypeDao {

    private static final Logger logger = LoggerFactory.getLogger(WoodTypeDaoImpl.class);

    public WoodTypeDaoImpl() {
        super(ReflectionBasedJdbcMapper.proxy(WoodType.class), WoodType.class, Long.class);
    }

    @Override
    public List<WoodType> allWoodTypes() {
        String sql = "SELECT * FROM " + this.getDomainMapper().getTablename();
        return getJdbcTemplate().query(sql, new Object[]{}, getDomainMapper());
    }
}
