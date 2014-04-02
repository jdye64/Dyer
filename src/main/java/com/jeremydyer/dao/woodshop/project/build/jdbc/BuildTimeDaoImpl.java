package com.jeremydyer.dao.woodshop.project.build.jdbc;

import com.jeremydyer.core.woodshop.project.build.BuildTime;
import com.jeremydyer.dao.woodshop.project.build.BuildTimeDao;
import com.makeandbuild.persistence.jdbc.BaseDaoImpl;
import com.makeandbuild.persistence.jdbc.ReflectionBasedJdbcMapper;

/**
 * User: Jeremy Dyer
 * Date: 4/2/14
 * Time: 10:35 AM
 */
public class BuildTimeDaoImpl
    extends BaseDaoImpl<BuildTime, Long>
    implements BuildTimeDao {

    public BuildTimeDaoImpl() {
        super(ReflectionBasedJdbcMapper.proxy(BuildTime.class), BuildTime.class, Long.class);
    }
}
