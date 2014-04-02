package com.jeremydyer.dao.woodshop.project.jdbc;

import com.jeremydyer.core.woodshop.project.ProjectStep;
import com.jeremydyer.dao.woodshop.project.ProjectStepDao;
import com.makeandbuild.persistence.jdbc.BaseDaoImpl;
import com.makeandbuild.persistence.jdbc.ReflectionBasedJdbcMapper;

/**
 * Implementation to persist and retrieve project steps for a particular project.
 *
 * User: Jeremy Dyer
 * Date: 4/2/14
 * Time: 10:32 AM
 */
public class ProjectStepDaoImpl
    extends BaseDaoImpl<ProjectStep, Long>
    implements ProjectStepDao {

    public ProjectStepDaoImpl() {
        super(ReflectionBasedJdbcMapper.proxy(ProjectStep.class), ProjectStep.class, Long.class);
    }
}
