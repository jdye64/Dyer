package com.jeremydyer.resource.woodshop.project.build;

import com.jeremydyer.core.woodshop.project.build.BuildCut;
import com.jeremydyer.dao.woodshop.project.build.BuildCutDao;
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
 * Time: 1:43 PM
 */
@Path("/wood/project/build/cut")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Service
public class BuildCutResource
        extends ResourceBase2<BuildCut, Long> {

    private static final Logger logger = LoggerFactory.getLogger(BuildCutResource.class);

    @Autowired
    private BuildCutDao buildCutDao;

    private ObjectMapper objectMapper;

    public BuildCutResource() {
        super(BuildCut.class);
    }

    @Override
    protected BaseDao<BuildCut, Long> getDao() {
        return buildCutDao;
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
