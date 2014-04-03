package com.jeremydyer.service.media;

import com.jeremydyer.core.media.Photo;
import com.sun.jersey.core.header.FormDataContentDisposition;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * User: Jeremy Dyer
 * Date: 4/3/14
 * Time: 9:44 AM
 */
public interface PhotoService {

    public Photo savePhoto(Long photoCategory, Long categoryId, InputStream photoInputStream,
                           FormDataContentDisposition photoDetails) throws IOException;
}
