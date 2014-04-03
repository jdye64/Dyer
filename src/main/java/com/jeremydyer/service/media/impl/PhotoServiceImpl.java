package com.jeremydyer.service.media.impl;

import com.jeremydyer.core.media.Photo;
import com.jeremydyer.dao.media.PhotoDao;
import com.jeremydyer.service.media.PhotoService;
import com.sun.jersey.core.header.FormDataContentDisposition;
import org.apache.poi.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;

/**
 * User: Jeremy Dyer
 * Date: 4/3/14
 * Time: 9:44 AM
 */
public class PhotoServiceImpl
    implements PhotoService {

    private static final Logger logger = LoggerFactory.getLogger(PhotoServiceImpl.class);

    private String fileSystemBasePath;


    public static final Long PHOTOCAT_PROJECT = 0l;
    public static final Long PHOTOCAT_BUILD = 1l;
    public static final Long PHOTOCAT_BUILD_STEP = 2l;


    @Autowired
    private PhotoDao photoDao;


    public Photo savePhoto(Long photoCategory, Long categoryId, InputStream photoInputStream, 
                           FormDataContentDisposition photoDetails) throws IOException {

        File photoWriteFile = new File(fileSystemBasePath + photoDetails.getName());
        logger.info("Saving photo to -> '" + photoWriteFile.getAbsolutePath() + "'");
        IOUtils.copy(photoInputStream, new FileOutputStream(photoWriteFile));

        Photo photo = new Photo();
        photo.setCategoryId(categoryId);
        photo.setPhotoCategory(photoCategory);
        photo.setName(photoDetails.getName());
        photo.setPhotoType(photoDetails.getType());
        photo.setSize(photoDetails.getSize());

        //TODO: Find a way to get this informtion
        photo.setWidthPixels(100);
        photo.setHeightPixels(100);

        photo = photoDao.save(photo);

        return photo;
    }

    public String getFileSystemBasePath() {
        return fileSystemBasePath;
    }

    public void setFileSystemBasePath(String fileSystemBasePath) {
        if (!fileSystemBasePath.endsWith(File.separator)) {
            this.fileSystemBasePath = fileSystemBasePath + File.separator;
        } else {
            this.fileSystemBasePath = fileSystemBasePath;
        }
        logger.info("Photo FileSystem Base Path -> '" + this.fileSystemBasePath + "'");
    }
}