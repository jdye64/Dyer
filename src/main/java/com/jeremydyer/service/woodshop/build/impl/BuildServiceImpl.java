package com.jeremydyer.service.woodshop.build.impl;

import com.jeremydyer.core.woodshop.WoodItem;
import com.jeremydyer.core.woodshop.project.ProjectCut;
import com.jeremydyer.core.woodshop.project.build.Build;
import com.jeremydyer.core.woodshop.project.build.BuildCut;
import com.jeremydyer.core.woodshop.project.build.BuildShoppingList;
import com.jeremydyer.dao.woodshop.WoodItemDao;
import com.jeremydyer.dao.woodshop.project.ProjectCutDao;
import com.jeremydyer.dao.woodshop.project.build.BuildCutDao;
import com.jeremydyer.dao.woodshop.project.build.BuildDao;
import com.jeremydyer.dao.woodshop.project.build.BuildTimeDao;
import com.jeremydyer.service.woodshop.WoodItemService;
import com.jeremydyer.service.woodshop.build.BuildService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User: Jeremy Dyer
 * Date: 4/2/14
 * Time: 10:45 AM
 */
@Service
public class BuildServiceImpl
    implements BuildService {

    private static final Logger logger = LoggerFactory.getLogger(BuildServiceImpl.class);

    @Autowired
    private BuildDao buildDao;

    @Autowired
    private BuildCutDao buildCutDao;

    @Autowired
    private BuildTimeDao buildTimeDao;

    @Autowired
    private WoodItemDao woodItemDao;

    @Autowired
    private ProjectCutDao projectCutDao;

    @Autowired
    private WoodItemService woodItemService;


    @Override
    public BuildShoppingList createShoppingListForBuild(Long projectBuildId) {

        Build build = buildDao.find(projectBuildId);

        BuildShoppingList shoppingList = new BuildShoppingList();
        shoppingList.setProjectId(build.getProjectId());
        shoppingList.setBuildId(build.getProjectBuildId());

        List<ProjectCut> projectCutList = projectCutDao.cutsForProject(build.getProjectId());

        for (ProjectCut cut : projectCutList) {
            WoodItem existingWood = null;
            try {

                BuildCut buildCut = null;

                try {
                    buildCut = buildCutDao.buildCutForProjectCut(build.getProjectBuildId(),
                            cut.getProjectCutId());
                } catch (EmptyResultDataAccessException ere) {
                    //Do nothing just means nothing was found
                }

                if (buildCut == null) {
                    existingWood = woodItemDao.findWoodItemForProjectCut(cut);
                    if (existingWood == null) {
                        //This means that we need to create a wood item and add it to the shopping cart because we don't have
                        // it in inventory.
                        shoppingList.addWoodItemToShoppingList(createWoodItemFromProjectCut(build, cut));
                    } else {
                        //We already have that item in our inventory. No need to buy more. But we need to mark it has used
                        // and create a new wood item that would be the result of the scrap of the board after it has been
                        // used for the project build.
                        WoodItem woodAfterCut = woodItemService.cutWood(existingWood, cut);
                        woodItemDao.update(woodAfterCut);

                        //Create a Project Cut from the wood that is in the current inventory.
                        BuildCut newCut = new BuildCut();
                        newCut.setProjectBuildId(build.getProjectBuildId());
                        newCut.setProjectCutId(cut.getProjectCutId());
                        newCut.setCutFromWoodItemId(existingWood.getWoodInventoryId());

                        newCut = buildCutDao.save(newCut);
                    }
                } else {
                    //BuildCut already exists.
                    logger.info("Build cut already exists");
                }

            } catch (EmptyResultDataAccessException ere) {
                shoppingList.addWoodItemToShoppingList(createWoodItemFromProjectCut(build, cut));
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }

        return shoppingList;
    }


    private WoodItem createWoodItemFromProjectCut(Build build, ProjectCut cut) {
        WoodItem wi = new WoodItem();
        wi.setWoodSpeciesId(cut.getWoodSpeciesId());
        wi.setWoodTypeId(cut.getWoodTypeId());
        wi.setWidth(cut.getWidth());
        wi.setHeight(cut.getHeight());
        wi.setLength(cut.getLength());
        return wi;
    }
}
