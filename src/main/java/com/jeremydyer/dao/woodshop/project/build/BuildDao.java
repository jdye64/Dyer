package com.jeremydyer.dao.woodshop.project.build;

import com.jeremydyer.core.woodshop.project.build.Build;
import com.makeandbuild.persistence.jdbc.BaseDao;

import java.util.List;

/**
 * User: Jeremy Dyer
 * Date: 4/2/14
 * Time: 10:31 AM
 */
public interface BuildDao
    extends BaseDao<Build, Long> {

    List<Build> allBuilds();
}
