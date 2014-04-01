package com.jeremydyer.core.woodshop;

import com.makeandbuild.persistence.jdbc.SaveWhen;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Represents a cut that must be made for a project.
 *
 * User: Jeremy Dyer
 * Date: 3/31/14
 * Time: 3:09 PM
 */
@Table(name = "project_cut")
public class ProjectCut
    implements Serializable {

    @Id
    @Column(name = "project_cut_id")
    @SaveWhen(insert = true, update = false)
    private Long projectCutId;

    @Column(name = "project_id")
    @SaveWhen(insert = true, update = true)
    private Long projectId;

    @Column(name = "wood_type_id")
    @SaveWhen(insert = true, update = true)
    private Long woodTypeId;

    @Column(name = "wood_species_id")
    @SaveWhen(insert = true, update = true)
    private Long woodSpeciesId;

    @Column(name = "width")
    @SaveWhen(insert = true, update = true)
    private float width;

    @Column(name = "height")
    @SaveWhen(insert = true, update = true)
    private float height;

    @Column(name = "length")
    @SaveWhen(insert = true, update = true)
    private float length;

    @Column(name = "description")
    @SaveWhen(insert = true, update = true)
    private String description;

    public Long getProjectCutId() {
        return projectCutId;
    }

    public void setProjectCutId(Long projectCutId) {
        this.projectCutId = projectCutId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getWoodTypeId() {
        return woodTypeId;
    }

    public void setWoodTypeId(Long woodTypeId) {
        this.woodTypeId = woodTypeId;
    }

    public Long getWoodSpeciesId() {
        return woodSpeciesId;
    }

    public void setWoodSpeciesId(Long woodSpeciesId) {
        this.woodSpeciesId = woodSpeciesId;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
