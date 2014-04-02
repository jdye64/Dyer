package com.jeremydyer.resource.woodshop.project;

import com.jeremydyer.core.woodshop.project.ProjectCut;
import com.jeremydyer.dao.woodshop.project.ProjectCutDao;
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
 * Time: 1:46 PM
 */
@Path("/wood/project/cut")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Service
public class ProjectCutResource
        extends ResourceBase2<ProjectCut, Long> {

    private static final Logger logger = LoggerFactory.getLogger(ProjectCutResource.class);

    @Autowired
    private ProjectCutDao projectCutDao;

    private ObjectMapper objectMapper;

    public ProjectCutResource() {
        super(ProjectCut.class);
    }

    @Override
    protected BaseDao<ProjectCut, Long> getDao() {
        return projectCutDao;
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