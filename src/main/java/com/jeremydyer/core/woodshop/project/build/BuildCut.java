package com.jeremydyer.core.woodshop.project.build;

import com.makeandbuild.persistence.jdbc.SaveWhen;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Represents a single cut made for a project build.
 *
 * User: Jeremy Dyer
 * Date: 4/2/14
 * Time: 9:39 AM
 */
@Table(name = "project_build_cut")
public class BuildCut
    implements Serializable {

    @Id
    @Column(name = "project_build_cut_id")
    @SaveWhen(insert = true, update = false)
    private Long projectBuildCutId;

    @Column(name = "project_build_id")
    @SaveWhen(insert = true, update = true)
    private Long projectBuildId;

    @Column(name = "project_cut_id")
    @SaveWhen(insert = true, update = true)
    private Long projectCutId;          //If this value is empty then you can assume that the cut was made by either
    // error or not following the steps properly.

    @Column(name = "cut_from_wooditem_id")
    @SaveWhen(insert = true, update = true)
    private Long cutFromWoodItemId;

    @Column(name = "created_wooditem_id")
    @SaveWhen(insert = true, update = true)
    private Long createdWoodItemId;

    public Long getProjectBuildCutId() {
        return projectBuildCutId;
    }

    public void setProjectBuildCutId(Long projectBuildCutId) {
        this.projectBuildCutId = projectBuildCutId;
    }

    public Long getProjectBuildId() {
        return projectBuildId;
    }

    public void setProjectBuildId(Long projectBuildId) {
        this.projectBuildId = projectBuildId;
    }

    public Long getProjectCutId() {
        return projectCutId;
    }

    public void setProjectCutId(Long projectCutId) {
        this.projectCutId = projectCutId;
    }

    public Long getCutFromWoodItemId() {
        return cutFromWoodItemId;
    }

    public void setCutFromWoodItemId(Long cutFromWoodItemId) {
        this.cutFromWoodItemId = cutFromWoodItemId;
    }

    public Long getCreatedWoodItemId() {
        return createdWoodItemId;
    }

    public void setCreatedWoodItemId(Long createdWoodItemId) {
        this.createdWoodItemId = createdWoodItemId;
    }
}
