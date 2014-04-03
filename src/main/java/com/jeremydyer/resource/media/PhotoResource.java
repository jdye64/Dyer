package com.jeremydyer.resource.media;

import com.jeremydyer.core.media.Photo;
import com.jeremydyer.dao.media.PhotoDao;
import com.jeremydyer.resource.ResourceBase2;
import com.makeandbuild.persistence.jdbc.BaseDao;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * User: Jeremy Dyer
 * Date: 4/3/14
 * Time: 9:32 AM
 */
@Path("/photo")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Service
public class PhotoResource
        extends ResourceBase2<Photo, Long> {

    private static final Logger logger = LoggerFactory.getLogger(PhotoResource.class);

    @Autowired
    private PhotoDao photoDao;

    private ObjectMapper objectMapper;

    public PhotoResource() {
        super(Photo.class);
    }

    @Override
    protected BaseDao<Photo, Long> getDao() {
        return photoDao;
    }

    @Override
    protected ObjectMapper getObjectMapper() {
        if (objectMapper == null){
            objectMapper = new ObjectMapper();
            objectMapper.getSerializationConfig().setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
            objectMapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);
        }
        return objectMapper;
    }
}