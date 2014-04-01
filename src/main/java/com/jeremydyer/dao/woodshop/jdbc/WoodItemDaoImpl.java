package com.jeremydyer.dao.woodshop.jdbc;

import com.jeremydyer.core.woodshop.ProjectCut;
import com.jeremydyer.core.woodshop.WoodItem;
import com.jeremydyer.dao.woodshop.WoodItemDao;
import com.makeandbuild.persistence.jdbc.BaseDaoImpl;
import com.makeandbuild.persistence.jdbc.ReflectionBasedJdbcMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * User: Jeremy Dyer
 * Date: 3/31/14
 * Time: 12:58 PM
 */
public class WoodItemDaoImpl
    extends BaseDaoImpl<WoodItem, Long>
    implements WoodItemDao {

    private static final Logger logger = LoggerFactory.getLogger(WoodItemDaoImpl.class);

    public WoodItemDaoImpl() {
        super(ReflectionBasedJdbcMapper.proxy(WoodItem.class), WoodItem.class, Long.class);
    }

    @Override
    public List<WoodItem> allWoodItems() {
        String sql = "SELECT * FROM " + this.getDomainMapper().getTablename();
        return getJdbcTemplate().query(sql, new Object[]{}, getDomainMapper());
    }

    @Override
    public List<WoodItem> retrieveCheckedOutItems() {
        String sql = "SELECT * FROM " + this.getDomainMapper().getTablename() + " WHERE checkout_status = ?";
        return getJdbcTemplate().query(sql, new Object[]{"CHECKED_OUT"}, getDomainMapper());
    }

    @Override
    public WoodItem findWoodItemForProjectCut(ProjectCut projectCut) {
        String sql = "SELECT * FROM " + this.getDomainMapper().getTablename() + " WHERE checkout_status != null AND wood_species_id = ? AND wood_type_id = ? AND ";
        return getJdbcTemplate().query(sql, new Object[]{projectCut}, getDomainMapper());
    }
}
