package com.jeremydyer.core.media;

import com.makeandbuild.persistence.jdbc.SaveWhen;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Represents a picture in the Dyer application.
 *
 * User: Jeremy Dyer
 * Date: 4/3/14
 * Time: 9:28 AM
 */
@Table(name = "photo")
public class Photo
    implements Serializable {

    @Id
    @Column(name = "photo_id")
    @SaveWhen(insert = true, update = false)
    private Long photoId;

    @Column(name = "photo_category")
    @SaveWhen(insert = true, update = true)
    private Long photoCategory;

    @Column(name = "category_id")
    @SaveWhen(insert = true, update = true)
    private Long categoryId;

    @Column(name = "name")
    @SaveWhen(insert = true, update = true)
    private String name;

    @Column(name = "size")
    @SaveWhen(insert = true, update = true)
    private Long size;

    @Column(name = "photo_type")
    @SaveWhen(insert = true, update = true)
    private String photoType;

    @Column(name = "width_pixels")
    @SaveWhen(insert = true, update = true)
    private int widthPixels;

    @Column(name = "height_pixels")
    @SaveWhen(insert = true, update = true)
    private int heightPixels;

    public Long getPhotoId() {
        return photoId;
    }

    public void setPhotoId(Long photoId) {
        this.photoId = photoId;
    }

    public Long getPhotoCategory() {
        return photoCategory;
    }

    public void setPhotoCategory(Long photoCategory) {
        this.photoCategory = photoCategory;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getPhotoType() {
        return photoType;
    }

    public void setPhotoType(String photoType) {
        this.photoType = photoType;
    }

    public int getWidthPixels() {
        return widthPixels;
    }

    public void setWidthPixels(int widthPixels) {
        this.widthPixels = widthPixels;
    }

    public int getHeightPixels() {
        return heightPixels;
    }

    public void setHeightPixels(int heightPixels) {
        this.heightPixels = heightPixels;
    }
}
