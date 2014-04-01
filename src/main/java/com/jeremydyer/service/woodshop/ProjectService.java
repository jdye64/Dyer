package com.jeremydyer.service.woodshop;

import com.jeremydyer.core.woodshop.ProjectShoppingList;

/**
 * User: Jeremy Dyer
 * Date: 3/31/14
 * Time: 3:24 PM
 */
public interface ProjectService {

    ProjectShoppingList createShoppingListForProject(Long projectId);
}
