package com.jeremydyer.dao.woodshop;

import com.jeremydyer.core.woodshop.project.ProjectCut;
import com.makeandbuild.persistence.jdbc.BaseDao;

import java.util.List;

/**
 * User: Jeremy Dyer
 * Date: 3/31/14
 * Time: 3:22 PM
 */
public interface ProjectCutDao
    extends BaseDao<ProjectCut, Long> {

    List<ProjectCut> cutsForProject(Long projectId);
}
