package com.jeremydyer.core.woodshop.project.build;

import com.jeremydyer.core.woodshop.WoodItem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Shopping list of materials for a particular project.
 *
 * User: Jeremy Dyer
 * Date: 4/1/14
 * Time: 9:51 AM
 */
public class BuildShoppingList
    implements Serializable {

    private Long projectId;
    private Long buildId;
    private List<WoodItem> wood = new ArrayList<WoodItem>();

    public BuildShoppingList() {}

    public void addWoodItemToShoppingList(WoodItem woodItem) {
        this.wood.add(woodItem);
    }


    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getBuildId() {
        return buildId;
    }

    public void setBuildId(Long buildId) {
        this.buildId = buildId;
    }

    public List<WoodItem> getWood() {
        return wood;
    }

    public void setWood(List<WoodItem> wood) {
        this.wood = wood;
    }
}