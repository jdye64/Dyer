package com.jeremydyer.core.woodshop;

import com.makeandbuild.persistence.jdbc.SaveWhen;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * User: Jeremy Dyer
 * Date: 3/31/14
 * Time: 11:02 AM
 */
@Table(name = "wood_item")
public class WoodItem
    implements Serializable {

    @Id
    @Column(name = "wood_item_id")
    @SaveWhen(insert = true, update = false)
    private Long woodInventoryId;

    @Column(name = "wood_species_id")
    @SaveWhen(insert = true, update = true)
    private Long woodSpeciesId;

    @Column(name = "wood_type_id")
    @SaveWhen(insert = true, update = true)
    private Long woodTypeId;

    @Column(name = "width")
    @SaveWhen(insert = true, update = true)
    private float width;

    @Column(name = "height")
    @SaveWhen(insert = true, update = true)
    private float height;

    @Column(name = "length")
    @SaveWhen(insert = true, update = true)
    private float length;

    public Long getWoodInventoryId() {
        return woodInventoryId;
    }

    public void setWoodInventoryId(Long woodInventoryId) {
        this.woodInventoryId = woodInventoryId;
    }

    public Long getWoodSpeciesId() {
        return woodSpeciesId;
    }

    public void setWoodSpeciesId(Long woodSpeciesId) {
        this.woodSpeciesId = woodSpeciesId;
    }

    public Long getWoodTypeId() {
        return woodTypeId;
    }

    public void setWoodTypeId(Long woodTypeId) {
        this.woodTypeId = woodTypeId;
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
}
