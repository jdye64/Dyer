package com.jeremydyer.service.woodshop.impl;

import com.jeremydyer.core.woodshop.ProjectCut;
import com.jeremydyer.core.woodshop.ProjectShoppingList;
import com.jeremydyer.core.woodshop.WoodItem;
import com.jeremydyer.dao.woodshop.ProjectCutDao;
import com.jeremydyer.dao.woodshop.ProjectDao;
import com.jeremydyer.dao.woodshop.WoodItemDao;
import com.jeremydyer.service.woodshop.ProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User: Jeremy Dyer
 * Date: 3/31/14
 * Time: 3:25 PM
 */
@Service
public class ProjectServiceImpl
    implements ProjectService {

    private static final Logger logger = LoggerFactory.getLogger(ProjectServiceImpl.class);

    @Autowired
    private ProjectDao projectDao;

    @Autowired
    private ProjectCutDao projectCutDao;

    @Autowired
    private WoodItemDao woodItemDao;        //Wood Inventory.

    @Override
    public ProjectShoppingList createShoppingListForProject(Long projectId) {
        ProjectShoppingList shoppingList = new ProjectShoppingList();
        shoppingList.setProjectId(projectId);

        List<WoodItem> woodInventory = woodItemDao.allWoodItems();
        List<ProjectCut> projectCutList = projectCutDao.cutsForProject(projectId);

        for (ProjectCut cut : projectCutList) {
            WoodItem woodItem = getWoodItemForCut(cut, woodInventory);
            if (woodItem != null && woodItem.getWoodInventoryId() == null) {
                logger.info("Adding WoodItem " + woodItem + " to shopping list");
                shoppingList.addWoodItemToShoppingList(woodItem);
            }
        }

        return shoppingList;
    }


    /**
     * Searches your inventory for a board that matches your needs. If that board isn't found then
     * a new WoodItem is created and returned so that it can be added to your shoppping cart.
     *
     * @param cut
     *      ProjectCut.
     *
     * @param woodInventory
     *      Your current wood inventory.
     *
     * @return
     */
    public WoodItem getWoodItemForCut(ProjectCut cut, List<WoodItem> woodInventory) {
        WoodItem woodItem = null;

        //Attempts to find a piece of wood in your inventory that might satisfy the cut that you need.
        boolean foundInInventory = false;
        for (WoodItem wood : woodInventory) {
            //First make sure the wood isn't already been marked for use in another project (checked out of inventory
            // for another project)
            if (wood.getUsedForProjectId() == null) {

                //Make sure the wood is of the proper type and species.
//                logger.info("Wood Type " + wood.getWoodTypeId() + " : " + cut.getWoodTypeId());
//                logger.info("Wood Species " + wood.getWoodSpeciesId() + " : " + cut.getWoodSpeciesId());
                if (wood.getWoodTypeId().equals(cut.getWoodTypeId()) && wood.getWoodSpeciesId().equals(cut
                        .getWoodSpeciesId())) {

                    if (cut.getWoodTypeId() == 1) {
                        //This is plywood so just make sure that we have enough board feet.
                        if (wood.getHeight() >= cut.getHeight() && wood.getWidth() >= cut.getWidth() && wood.getLength()
                                >= cut.getLength()) {
                            woodItem = wood;
                            foundInInventory = true;
                            break;
                        }
                    } else {
                        //This is a board so the width and height must match perfectly.
                        if (wood.getHeight() == cut.getHeight() && wood.getWidth() == cut.getWidth() && wood
                                .getLength()
                                >= cut.getLength()) {
                            woodItem = wood;
                            foundInInventory = true;
                            break;
                        }
                    }
                }
            }
        }

        //Creates the WoodItem that needs to be purchased.
        if (!foundInInventory) {
            woodItem = new WoodItem();
            woodItem.setWoodSpeciesId(cut.getWoodTypeId());
            woodItem.setWoodTypeId(cut.getWoodTypeId());
            woodItem.setHeight(cut.getHeight());
            woodItem.setWidth(cut.getWidth());
            woodItem.setLength(cut.getLength());
        }

        return woodItem;
    }
}
