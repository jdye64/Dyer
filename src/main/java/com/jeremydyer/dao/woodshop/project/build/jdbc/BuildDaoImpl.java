package com.jeremydyer.dao.woodshop.project.build.jdbc;

import com.jeremydyer.core.woodshop.project.build.Build;
import com.jeremydyer.dao.woodshop.project.build.BuildDao;
import com.makeandbuild.persistence.jdbc.BaseDaoImpl;
import com.makeandbuild.persistence.jdbc.ReflectionBasedJdbcMapper;

/**
 * User: Jeremy Dyer
 * Date: 4/2/14
 * Time: 10:37 AM
 */
public class BuildDaoImpl
    extends BaseDaoImpl<Build, Long>
    implements BuildDao {

    public BuildDaoImpl() {
        super(ReflectionBasedJdbcMapper.proxy(Build.class), Build.class, Long.class);
    }
}
