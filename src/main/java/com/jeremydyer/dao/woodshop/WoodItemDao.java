package com.jeremydyer.dao.woodshop;

import com.jeremydyer.core.woodshop.project.ProjectCut;
import com.jeremydyer.core.woodshop.WoodItem;
import com.makeandbuild.persistence.jdbc.BaseDao;

import java.util.List;

/**
 * User: Jeremy Dyer
 * Date: 3/31/14
 * Time: 12:57 PM
 */
public interface WoodItemDao
    extends BaseDao<WoodItem, Long> {

    List<WoodItem> allWoodItems();

    List<WoodItem> retrieveCheckedOutItems();

    WoodItem findWoodItemForProjectCut(ProjectCut projectCut);
}
