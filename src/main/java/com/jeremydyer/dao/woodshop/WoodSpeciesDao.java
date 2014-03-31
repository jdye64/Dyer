package com.jeremydyer.dao.woodshop;

import com.jeremydyer.core.woodshop.WoodSpecies;
import com.makeandbuild.persistence.jdbc.BaseDao;

import java.util.List;

/**
 * User: Jeremy Dyer
 * Date: 3/31/14
 * Time: 12:54 PM
 */
public interface WoodSpeciesDao
        extends BaseDao<WoodSpecies, Long> {

    List<WoodSpecies> allWoodSpecies();
}