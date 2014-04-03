package com.jeremydyer.resource.woodshop.project;

import com.jeremydyer.core.media.Photo;
import com.jeremydyer.core.woodshop.project.Project;
import com.jeremydyer.dao.woodshop.project.ProjectDao;
import com.jeremydyer.resource.ResourceBase2;
import com.jeremydyer.service.media.PhotoService;
import com.jeremydyer.service.media.impl.PhotoServiceImpl;
import com.makeandbuild.persistence.AbstractPagedRequest;
import com.makeandbuild.persistence.Criteria;
import com.makeandbuild.persistence.jdbc.BaseDao;
import com.makeandbuild.persistence.jdbc.PagedResponse;
import com.makeandbuild.persistence.jdbc.SortBy;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;
import com.yammer.metrics.annotation.Timed;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * User: Jeremy Dyer
 * Date: 3/31/14
 * Time: 3:24 PM
 */
@Path("/wood/project")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Service
public class ProjectResource
    extends ResourceBase2<Project, Long> {

    private static final Logger logger = LoggerFactory.getLogger(ProjectResource.class);

    @Autowired
    private ProjectDao projectDao;

    @Autowired
    private PhotoService photoService;


    private ObjectMapper objectMapper;

    public ProjectResource() {
        super(Project.class);
    }


    @POST
    @Path("/{projectId}/photo")
    @Timed
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadProjectPhoto(@PathParam("projectId") Long projectId, @FormDataParam("file") final InputStream
            stream, @FormDataParam("file") FormDataContentDisposition fileDetail) {
        try {
            Photo savedPhoto = photoService.savePhoto(PhotoServiceImpl.PHOTOCAT_PROJECT, projectId, stream, fileDetail);
            return Response.ok().entity(savedPhoto).build();
        } catch(Exception e) {
            throw new RestClientException(e.getMessage());
        }
    }


    @Override
    protected BaseDao<Project, Long> getDao() {
        return projectDao;
    }

    @Override
    protected ObjectMapper getObjectMapper() {
        if (objectMapper == null){
            objectMapper = new ObjectMapper();
            objectMapper.getSerializationConfig().setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
            objectMapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);
//            SimpleModule testModule = new SimpleModule("MyModule", new Version(1, 0, 0, null));
//            testModule.addSerializer(License.class, licenseSerializer);
//            testModule.addSerializer(CompanyAccount.class, companyAccountSerializer);
//            testModule.addSerializer((Class<PagedResponse<License>>)(Class<?>)PagedResponse.class, new PagedResponseSerializer<License>(License.class));
//            objectMapper.registerModule(testModule);
        }
        return objectMapper;
    }
}
