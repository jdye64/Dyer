package com.jeremydyer.dao.media.jdbc;

import com.jeremydyer.core.media.Photo;
import com.jeremydyer.dao.media.PhotoDao;
import com.makeandbuild.persistence.jdbc.BaseDaoImpl;
import com.makeandbuild.persistence.jdbc.ReflectionBasedJdbcMapper;

import java.util.List;

/**
 * User: Jeremy Dyer
 * Date: 4/3/14
 * Time: 9:29 AM
 */
public class PhotoDaoImpl
    extends BaseDaoImpl<Photo, Long>
    implements PhotoDao {

    public PhotoDaoImpl() {
        super(ReflectionBasedJdbcMapper.proxy(Photo.class), Photo.class, Long.class);
    }

    @Override
    public List<Photo> photosForCategoryAndCategoryId(Long category, Long categoryId) {
        String sql = "SELECT * FROM " + this.getDomainMapper().getTablename() + " WHERE photo_category = ? AND category_id = ?";
        return getJdbcTemplate().query(sql, new Object[]{category, categoryId}, getDomainMapper());
    }
}
