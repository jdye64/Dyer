package com.jeremydyer.service.media.impl;

import com.jeremydyer.core.media.Photo;
import com.jeremydyer.dao.media.PhotoDao;
import com.jeremydyer.service.media.PhotoService;
import com.sun.jersey.core.header.FormDataContentDisposition;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
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

        File photoWriteFile = createPhotoUploadFilePath(photoDetails);
        logger.info("Saving photo to -> '" + photoWriteFile.getAbsolutePath() + "'");
        byte[] imageData = IOUtils.toByteArray(photoInputStream);
        IOUtils.copy(new ByteArrayInputStream(imageData), new FileOutputStream(photoWriteFile));

        Photo photo = new Photo();
        photo.setCategoryId(categoryId);
        photo.setPhotoCategory(photoCategory);
        photo.setOriginalName(photoWriteFile.getName());
        photo.setName(photoDetails.getFileName());
        photo.setPhotoType(FilenameUtils.getExtension(photoDetails.getFileName()));
        photo.setSize(photoWriteFile.length());

        BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageData));
        photo.setWidthPixels(image.getWidth());
        photo.setHeightPixels(image.getHeight());

        photo = photoDao.save(photo);

        return photo;
    }


    /**
     * Creates the location where the photo will be saved to.
     *
     * @param photoDetails
     * @return
     */
    private File createPhotoUploadFilePath(FormDataContentDisposition photoDetails) {
        File file = null;

        if (photoDetails.getFileName() != null) {
            String ext = FilenameUtils.getExtension(photoDetails.getFileName());
            file = new File(this.fileSystemBasePath + new Long(System.nanoTime()).toString() + "." + ext);
        } else {
            file = new File(this.fileSystemBasePath + new Long(System.nanoTime()).toString());
        }

        return file;
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
