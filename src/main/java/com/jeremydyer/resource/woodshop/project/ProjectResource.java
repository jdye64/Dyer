package com.jeremydyer.resource.woodshop.project;

import com.jeremydyer.core.woodshop.project.Project;
import com.jeremydyer.dao.woodshop.project.ProjectDao;
import com.jeremydyer.resource.ResourceBase2;
import com.makeandbuild.persistence.jdbc.BaseDao;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

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

    private ObjectMapper objectMapper;


    public ProjectResource() {
        super(Project.class);
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
