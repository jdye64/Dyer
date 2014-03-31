package com.jeremydyer.dao.woodshop.jdbc;

import com.jeremydyer.core.woodshop.ProjectCut;
import com.jeremydyer.dao.woodshop.ProjectCutDao;
import com.makeandbuild.persistence.jdbc.BaseDaoImpl;
import com.makeandbuild.persistence.jdbc.ReflectionBasedJdbcMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * User: Jeremy Dyer
 * Date: 3/31/14
 * Time: 3:22 PM
 */
public class ProjectCutDaoImpl
    extends BaseDaoImpl<ProjectCut, Long>
    implements ProjectCutDao {

    private static final Logger logger = LoggerFactory.getLogger(ProjectCutDaoImpl.class);

    public ProjectCutDaoImpl() {
        super(ReflectionBasedJdbcMapper.proxy(ProjectCut.class), ProjectCut.class, Long.class);
    }
}
