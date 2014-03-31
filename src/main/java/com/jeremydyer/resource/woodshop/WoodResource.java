package com.jeremydyer.resource.woodshop;

import com.jeremydyer.core.woodshop.WoodItem;
import com.jeremydyer.core.woodshop.WoodSpecies;
import com.jeremydyer.core.woodshop.WoodType;
import com.jeremydyer.dao.woodshop.WoodItemDao;
import com.jeremydyer.dao.woodshop.WoodSpeciesDao;
import com.jeremydyer.dao.woodshop.WoodTypeDao;
import com.yammer.metrics.annotation.Timed;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


/**
 * User: Jeremy Dyer
 * Date: 3/31/14
 * Time: 1:04 PM
 */
@Path("/wood")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Service
public class WoodResource {

    private static final Logger logger = Logger.getLogger(WoodResource.class);

    @Autowired
    private WoodItemDao woodItemDao;

    @Autowired
    private WoodSpeciesDao woodSpeciesDao;

    @Autowired
    private WoodTypeDao woodTypeDao;


    @GET
    @Timed
    @Path("/species")
    public Response retrieveWoodSpecies() {
        try {
            List<WoodSpecies> woodSpecies = woodSpeciesDao.allWoodSpecies();
            logger.info(woodSpecies.size() + " WoodSpecies");
            return Response.ok(woodSpecies).build();
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GET
    @Timed
    @Path("/species/{speciesId}")
    public Response retrieveWoodSpecies(@PathParam("speciesId") Long speciesId) {
        try {
            return Response.ok(woodSpeciesDao.find(speciesId)).build();
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }


    @PUT
    @Timed
    @Path("/species")
    @Produces(MediaType.TEXT_HTML)
    public Response putWoodSpecies(WoodSpecies woodSpecies) {
        try {
            woodSpeciesDao.save(woodSpecies);
            return Response.status(201).build();
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }


    @POST
    @Timed
    @Path("/species")
    public Response updateWoodSpecies(WoodSpecies woodSpecies) {
        try {
            woodSpeciesDao.update(woodSpecies);
            return Response.status(200).build();
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GET
    @Timed
    @Path("/type")
    public Response retrieveWoodTypes() {
        try {
            List<WoodType> woodTypes = woodTypeDao.allWoodTypes();
            logger.info(woodTypes.size() + " WoodTypes");
            return Response.ok(woodTypes).build();
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GET
    @Timed
    @Path("/type/{woodTypeId}")
    public Response retrieveWoodType(@PathParam("woodTypeId") Long woodTypeId) {
        try {
            return Response.ok(woodTypeDao.find(woodTypeId)).build();
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }


    @PUT
    @Timed
    @Path("/type")
    public Response putWoodType(WoodType woodType) {
        try {
            woodTypeDao.save(woodType);
            return Response.status(201).build();
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }


    @POST
    @Timed
    @Path("/type")
    public Response updateWoodType(WoodType woodType) {
        try {
            woodTypeDao.update(woodType);
            return Response.status(200).build();
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GET
    @Timed
    @Path("/item")
    public Response retrieveWoodItems() {
        try {
            List<WoodItem> woodItems = woodItemDao.allWoodItems();
            logger.info(woodItems.size() + " WoodItems");
            return Response.ok(woodItems).build();
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GET
    @Timed
    @Path("/item/{woodItemId}")
    public Response retrieveWoodItem(@PathParam("woodItemId") Long woodItemId) {
        try {
            return Response.ok(woodItemDao.find(woodItemId)).build();
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }


    @PUT
    @Timed
    @Path("/item")
    public Response putWoodItem(WoodItem woodItem) {
        try {
            woodItemDao.save(woodItem);
            return Response.status(201).build();
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }


    @POST
    @Timed
    @Path("/item")
    public Response updateWoodItem(WoodItem woodItem) {
        try {
            woodItemDao.update(woodItem);
            return Response.status(200).build();
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
