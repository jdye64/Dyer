package com.jeremydyer.service.woodshop;

import com.jeremydyer.core.woodshop.WoodItem;
import com.jeremydyer.core.woodshop.project.ProjectCut;

/**
 * User: Jeremy Dyer
 * Date: 4/2/14
 * Time: 11:04 AM
 */
public interface WoodItemService {

    WoodItem cutWood(WoodItem woodItem, ProjectCut cut);
}
