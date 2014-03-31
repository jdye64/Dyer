package com.jeremydyer.dao.network;

import com.jeremydyer.core.network.NetworkLocation;
import com.makeandbuild.persistence.jdbc.BaseDao;

import java.util.List;

/**
 * Created by jeremydyer on 3/27/14.
 */
public interface NetworkLocationDao
    extends BaseDao<NetworkLocation, Long> {

    List<NetworkLocation> networkLocationsForUser(Long userId);
}
