package com.jeremydyer.resource.woodshop.project.build;

import com.jeremydyer.core.woodshop.project.build.BuildTime;
import com.jeremydyer.dao.woodshop.project.build.BuildTimeDao;
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
 * Date: 4/2/14
 * Time: 1:45 PM
 */
@Path("/wood/project/build/time")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Service
public class BuildTimeResource
        extends ResourceBase2<BuildTime, Long> {

    private static final Logger logger = LoggerFactory.getLogger(BuildTimeResource.class);

    @Autowired
    private BuildTimeDao buildTimeDao;

    private ObjectMapper objectMapper;

    public BuildTimeResource() {
        super(BuildTime.class);
    }

    @Override
    protected BaseDao<BuildTime, Long> getDao() {
        return buildTimeDao;
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

