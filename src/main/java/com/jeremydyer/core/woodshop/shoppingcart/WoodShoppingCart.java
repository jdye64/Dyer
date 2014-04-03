package com.jeremydyer.core.woodshop.shoppingcart;

import com.makeandbuild.persistence.jdbc.SaveWhen;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * User: Jeremy Dyer
 * Date: 4/2/14
 * Time: 12:52 PM
 */
@Table(name = "wood_shopping_cart")
public class WoodShoppingCart
    implements Serializable {

    @Id
    @Column(name = "wood_shopping_cart_id")
    @SaveWhen(insert = true, update = false)
    private Long woodShoppingCartId;

    @Column(name = "build_id")
    @SaveWhen(insert = true, update = true)
    private Long buildId;

    @Column(name = "project_cut_id")
    @SaveWhen(insert = true, update = true)
    private Long projectCutId;

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

    public Long getWoodShoppingCartId() {
        return woodShoppingCartId;
    }

    public void setWoodShoppingCartId(Long woodShoppingCartId) {
        this.woodShoppingCartId = woodShoppingCartId;
    }

    public Long getBuildId() {
        return buildId;
    }

    public void setBuildId(Long buildId) {
        this.buildId = buildId;
    }

    public Long getProjectCutId() {
        return projectCutId;
    }

    public void setProjectCutId(Long projectCutId) {
        this.projectCutId = projectCutId;
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
