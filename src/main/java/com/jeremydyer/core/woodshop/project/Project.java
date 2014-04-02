package com.jeremydyer.core.woodshop.project;

import com.makeandbuild.persistence.jdbc.SaveWhen;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * User: Jeremy Dyer
 * Date: 3/31/14
 * Time: 3:05 PM
 */
@Table(name = "project")
public class Project
    implements Serializable {

    @Id
    @Column(name = "project_id")
    @SaveWhen(insert = true, update = false)
    private Long woodInventoryId;

    @Column(name = "name")
    @SaveWhen(insert = true, update = true)
    private String name;

    @Column(name = "description")
    @SaveWhen(insert = true, update = true)
    private String description;

    public Long getWoodInventoryId() {
        return woodInventoryId;
    }

    public void setWoodInventoryId(Long woodInventoryId) {
        this.woodInventoryId = woodInventoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
