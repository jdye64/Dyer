package com.jeremydyer.core.woodshop;

import com.makeandbuild.persistence.jdbc.SaveWhen;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Holds the type of wood. Trim, cove moulding, board, plywood, etc.
 *
 * User: Jeremy Dyer
 * Date: 3/31/14
 * Time: 11:34 AM
 */
@Table(name = "wood_type")
public class WoodType
    implements Serializable {

    @Id
    @Column(name = "wood_type_id")
    @SaveWhen(insert = true, update = false)
    private Long woodTypeId;

    @Column(name = "name")
    @NotNull
    @Max(255)
    @SaveWhen(insert = true, update = true)
    private String name;

    @Column(name = "description")
    @NotNull
    @Max(255)
    @SaveWhen(insert = true, update = true)
    private String description;

    public Long getWoodTypeId() {
        return woodTypeId;
    }

    public void setWoodTypeId(Long woodTypeId) {
        this.woodTypeId = woodTypeId;
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
