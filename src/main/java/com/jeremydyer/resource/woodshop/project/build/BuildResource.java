package com.jeremydyer.resource.woodshop.project.build;

import com.jeremydyer.core.woodshop.project.build.Build;
import com.jeremydyer.core.woodshop.project.build.BuildShoppingList;
import com.jeremydyer.dao.woodshop.project.build.BuildDao;
import com.jeremydyer.service.woodshop.build.BuildService;
import com.yammer.metrics.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * User: Jeremy Dyer
 * Date: 4/2/14
 * Time: 1:12 PM
 */
@Path("/wood/project/build")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Service
public class BuildResource {

    private static final Logger logger = LoggerFactory.getLogger(BuildResource.class);

    @Autowired
    private BuildService buildService;

    @Autowired
    private BuildDao buildDao;

    @GET
    @Timed
    public Response retrieveBuilds() {
        try {
            List<Build> builds = buildDao.allBuilds();
            return Response.ok(builds).build();
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex.getMessage(), ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Timed
    @Path("/{buildId}/shoppinglist")
    /**
     * Gets the list shopping list needed to build a project given your current inventory.
     */
    public Response retrieveBuildShoppingList(@PathParam("buildId") Long buildId) {
        try {
            logger.info("Gather shopping list for build " + buildId);
            BuildShoppingList shoppingList = buildService.createShoppingListForBuild(buildId);
            return Response.ok(shoppingList).build();
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex.getMessage(), ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
