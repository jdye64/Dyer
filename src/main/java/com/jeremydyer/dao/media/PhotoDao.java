package com.jeremydyer.dao.media;

import com.jeremydyer.core.media.Photo;
import com.makeandbuild.persistence.jdbc.BaseDao;

import java.util.List;

/**
 * User: Jeremy Dyer
 * Date: 4/3/14
 * Time: 9:29 AM
 */
public interface PhotoDao
    extends BaseDao<Photo, Long> {

    List<Photo> photosForCategoryAndCategoryId(Long category, Long categoryId);
}
