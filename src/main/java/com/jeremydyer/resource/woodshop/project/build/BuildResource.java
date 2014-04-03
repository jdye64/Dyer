package com.jeremydyer.resource.woodshop.project.build;

import com.jeremydyer.core.media.Photo;
import com.jeremydyer.core.woodshop.project.build.Build;
import com.jeremydyer.core.woodshop.project.build.BuildShoppingList;
import com.jeremydyer.dao.woodshop.project.build.BuildDao;
import com.jeremydyer.resource.ResourceBase2;
import com.jeremydyer.service.media.PhotoService;
import com.jeremydyer.service.media.impl.PhotoServiceImpl;
import com.jeremydyer.service.woodshop.build.BuildService;
import com.makeandbuild.persistence.ObjectNotFoundException;
import com.makeandbuild.persistence.jdbc.BaseDao;
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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.InputStream;

/**
 * User: Jeremy Dyer
 * Date: 4/2/14
 * Time: 1:12 PM
 */
@Path("/wood/project/build")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Service
public class BuildResource
        extends ResourceBase2<Build, Long> {

    private static final Logger logger = LoggerFactory.getLogger(BuildResource.class);

    @Autowired
    private BuildDao buildDao;

    @Autowired
    private PhotoService photoService;

    @Autowired
    private BuildService buildService;

    private ObjectMapper objectMapper;

    public BuildResource() {
        super(Build.class);
    }


    @POST
    @Path("/{buildId}/photo")
    @Timed
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadBuildPhoto(@PathParam("buildId") Long buildId, @FormDataParam("file") final InputStream
            stream, @FormDataParam("file") FormDataContentDisposition fileDetail) {
        try {
            Photo savedPhoto = photoService.savePhoto(PhotoServiceImpl.PHOTOCAT_BUILD, buildId, stream, fileDetail);
            return Response.ok().entity(savedPhoto).build();
        } catch(Exception e) {
            throw new RestClientException(e.getMessage());
        }
    }


    @GET
    @Path("/{buildId}/shoppingcart")
    @Timed
    public Response getBuildShoppingCart(@PathParam("buildId") Long buildId) {
        try {
            BuildShoppingList shoppingList = buildService.createShoppingListForBuild(buildId);
            return Response.ok().entity(shoppingList).build();
        } catch(ObjectNotFoundException e) {
            return Response.status(400).build();
        } catch(Exception e) {
            logger.warn("error with id "+buildId, e);
            throw new RestClientException("resource with id "+buildId+" not found");
        }
    }


    @Override
    protected BaseDao<Build, Long> getDao() {
        return buildDao;
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
