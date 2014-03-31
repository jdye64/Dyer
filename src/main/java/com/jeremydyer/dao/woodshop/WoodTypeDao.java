package com.jeremydyer.dao.woodshop;

import com.jeremydyer.core.woodshop.WoodType;
import com.makeandbuild.persistence.jdbc.BaseDao;

import java.util.List;

/**
 * User: Jeremy Dyer
 * Date: 3/31/14
 * Time: 11:40 AM
 */
public interface WoodTypeDao
        extends BaseDao<WoodType, Long> {

    List<WoodType> allWoodTypes();
}
