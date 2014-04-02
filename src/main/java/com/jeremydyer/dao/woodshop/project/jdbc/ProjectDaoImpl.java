package com.jeremydyer.dao.woodshop.project.jdbc;

import com.jeremydyer.core.woodshop.project.Project;
import com.jeremydyer.dao.woodshop.project.ProjectDao;
import com.makeandbuild.persistence.jdbc.BaseDaoImpl;
import com.makeandbuild.persistence.jdbc.ReflectionBasedJdbcMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * User: Jeremy Dyer
 * Date: 3/31/14
 * Time: 3:20 PM
 */
public class ProjectDaoImpl
    extends BaseDaoImpl<Project, Long>
    implements ProjectDao {

    private static final Logger logger = LoggerFactory.getLogger(ProjectDaoImpl.class);

    public ProjectDaoImpl() {
        super(ReflectionBasedJdbcMapper.proxy(Project.class), Project.class, Long.class);
    }

    @Override
    public List<Project> retrieveAllProjects() {
        String sql = "SELECT * FROM " + this.getDomainMapper().getTablename();
        return getJdbcTemplate().query(sql, new Object[]{}, getDomainMapper());
    }
}
