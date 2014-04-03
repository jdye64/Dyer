package com.jeremydyer.dao.woodshop.project.build.jdbc;

import com.jeremydyer.core.woodshop.project.build.BuildCut;
import com.jeremydyer.dao.woodshop.project.build.BuildCutDao;
import com.makeandbuild.persistence.jdbc.BaseDaoImpl;
import com.makeandbuild.persistence.jdbc.ReflectionBasedJdbcMapper;

/**
 * User: Jeremy Dyer
 * Date: 4/2/14
 * Time: 10:38 AM
 */
public class BuildCutDaoImpl
    extends BaseDaoImpl<BuildCut, Long>
    implements BuildCutDao {

    public BuildCutDaoImpl() {
        super(ReflectionBasedJdbcMapper.proxy(BuildCut.class), BuildCut.class, Long.class);
    }

    @Override
    public BuildCut buildCutForProjectCut(Long buildId, Long projectCutId) {
        String sql = "SELECT * FROM " + this.getDomainMapper().getTablename() + " WHERE project_build_id = ? AND project_cut_id = ?";
        return getJdbcTemplate().queryForObject(sql, new Object[]{buildId, projectCutId}, getDomainMapper());
    }
}
