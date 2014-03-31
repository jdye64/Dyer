package com.jeremydyer.resource.woodshop;

import com.jeremydyer.core.woodshop.Project;
import com.jeremydyer.dao.woodshop.ProjectDao;
import com.jeremydyer.service.woodshop.ProjectService;
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
 * Date: 3/31/14
 * Time: 3:24 PM
 */
@Path("/wood/project")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Service
public class ProjectResource {

    private static final Logger logger = LoggerFactory.getLogger(ProjectResource.class);

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ProjectDao projectDao;


    @GET
    @Timed
    public Response retrieveProjects() {
        try {
            List<Project> projects = projectDao.retrieveAllProjects();
            return Response.ok(projects).build();
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GET
    @Timed
    @Path("/{projectId}")
    public Response retrieveProject(@PathParam("projectId") Long projectId) {
        try {
            Project project = projectDao.find(projectId);
            return Response.ok(project).build();
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }


    @PUT
    @Timed
    public Response putProject(Project project) {
        try {
            return Response.ok(projectDao.save(project)).build();
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }


    @POST
    @Timed
    public Response updateProject(Project project) {
        try {
            return Response.ok(projectDao.update(project)).build();
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }


    @DELETE
    @Timed
    @Path("/{projectId}")
    public Response deleteProject(@PathParam("projectId") Long projectId) {
        try {
            projectDao.deleteById(projectId);
            return Response.ok().build();
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

}
