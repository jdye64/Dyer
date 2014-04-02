package com.jeremydyer.dao.woodshop.project;

import com.jeremydyer.core.woodshop.project.Project;
import com.makeandbuild.persistence.jdbc.BaseDao;

import java.util.List;

/**
 * User: Jeremy Dyer
 * Date: 3/31/14
 * Time: 3:20 PM
 */
public interface ProjectDao
    extends BaseDao<Project, Long> {

    List<Project> retrieveAllProjects();
}
