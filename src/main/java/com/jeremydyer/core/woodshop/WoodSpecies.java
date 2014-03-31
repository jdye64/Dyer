package com.jeremydyer.core.woodshop;

import com.makeandbuild.persistence.jdbc.SaveWhen;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * User: Jeremy Dyer
 * Date: 3/31/14
 * Time: 11:11 AM
 */
@Table(name = "wood_species")
public class WoodSpecies
    implements Serializable {

    @Id
    @Column(name = "wood_species_id")
    @SaveWhen(insert = true, update = false)
    private Long woodSpeciesId;

    @Column(name = "name")
    @SaveWhen(insert = true, update = true)
    @NotNull
    private String name;

    @Column(name = "description")
    @SaveWhen(insert = true, update = true)
    private String description;


    public Long getWoodSpeciesId() {
        return woodSpeciesId;
    }

    public void setWoodSpeciesId(Long woodSpeciesId) {
        this.woodSpeciesId = woodSpeciesId;
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
