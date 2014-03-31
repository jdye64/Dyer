package com.jeremydyer.dao.woodshop.jdbc;

import com.jeremydyer.core.woodshop.WoodSpecies;
import com.jeremydyer.dao.woodshop.WoodSpeciesDao;
import com.makeandbuild.persistence.jdbc.BaseDaoImpl;
import com.makeandbuild.persistence.jdbc.ReflectionBasedJdbcMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * User: Jeremy Dyer
 * Date: 3/31/14
 * Time: 12:55 PM
 */
public class WoodSpeciesDaoImpl
        extends BaseDaoImpl<WoodSpecies, Long>
        implements WoodSpeciesDao {

    private static final Logger logger = LoggerFactory.getLogger(WoodTypeDaoImpl.class);

    public WoodSpeciesDaoImpl() {
        super(ReflectionBasedJdbcMapper.proxy(WoodSpecies.class), WoodSpecies.class, Long.class);
    }

    @Override
    public List<WoodSpecies> allWoodSpecies() {
        String sql = "SELECT * FROM " + this.getDomainMapper().getTablename();
        return getJdbcTemplate().query(sql, new Object[]{}, getDomainMapper());
    }
}
