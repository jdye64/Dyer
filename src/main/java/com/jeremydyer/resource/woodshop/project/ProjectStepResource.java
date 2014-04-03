package com.jeremydyer.resource.woodshop.project;

import com.jeremydyer.core.media.Photo;
import com.jeremydyer.core.woodshop.project.ProjectStep;
import com.jeremydyer.dao.woodshop.project.ProjectStepDao;
import com.jeremydyer.resource.ResourceBase2;
import com.jeremydyer.resource.serializers.ProjectStepSerializer;
import com.jeremydyer.service.media.PhotoService;
import com.jeremydyer.service.media.impl.PhotoServiceImpl;
import com.makeandbuild.persistence.jdbc.BaseDao;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;
import com.yammer.metrics.annotation.Timed;
import org.codehaus.jackson.Version;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.module.SimpleModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.InputStream;

/**
 * User: Jeremy Dyer
 * Date: 4/2/14
 * Time: 1:48 PM
 */
@Path("/wood/project/step")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Service
public class ProjectStepResource
        extends ResourceBase2<ProjectStep, Long> {

    private static final Logger logger = LoggerFactory.getLogger(ProjectStepResource.class);

    @Autowired
    private ProjectStepDao projectStepDao;

    @Autowired
    private PhotoService photoService;

    @Autowired
    private ProjectStepSerializer projectStepSerializer;

    private ObjectMapper objectMapper;

    public ProjectStepResource() {
        super(ProjectStep.class);
    }


    @POST
    @Path("/{stepId}/photo")
    @Timed
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadProjectCutPhoto(@PathParam("stepId") Long stepId, @FormDataParam("file") final InputStream
            stream, @FormDataParam("file") FormDataContentDisposition fileDetail) {
        try {
            Photo savedPhoto = photoService.savePhoto(PhotoServiceImpl.PHOTOCAT_BUILD_STEP, stepId, stream, fileDetail);
            return Response.ok().entity(savedPhoto).build();
        } catch(Exception e) {
            throw new RestClientException(e.getMessage());
        }
    }


    @Override
    protected BaseDao<ProjectStep, Long> getDao() {
        return projectStepDao;
    }

    @Override
    protected ObjectMapper getObjectMapper() {
        if (objectMapper == null){
            objectMapper = new ObjectMapper();
            objectMapper.getSerializationConfig().setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
            objectMapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);
            SimpleModule simpleModule = new SimpleModule("ProjectStep", new Version(1, 0, 0, null));
            simpleModule.addSerializer(ProjectStep.class, projectStepSerializer);
            objectMapper.registerModule(simpleModule);
        }
        return objectMapper;
    }
}