package com.jeremydyer.service.woodshop.build;

import com.jeremydyer.core.woodshop.project.build.BuildShoppingList;

/**
 * Service for implementing the Build information.
 *
 * User: Jeremy Dyer
 * Date: 4/2/14
 * Time: 10:45 AM
 */
public interface BuildService {

    BuildShoppingList createShoppingListForBuild(Long projectBuildId);
}
