package com.jeremydyer.resource.woodshop;

import com.jeremydyer.core.woodshop.WoodItem;
import com.jeremydyer.service.woodshop.WoodInventoryService;
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
 * Time: 2:10 PM
 */
@Path("/wood/inventory")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Service
public class WoodInventoryResource {

    private static final Logger logger = LoggerFactory.getLogger(WoodInventoryResource.class);

    @Autowired
    private WoodInventoryService woodInventoryService;


    @GET
    @Timed
    public Response retrieveWoodInventory() {
        try {
            List<WoodItem> inventory = woodInventoryService.retrieveInventory();
            return Response.ok(inventory).build();
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GET
    @Timed
    @Path("/{woodItemId}")
    public Response retrieveWoodInventoryItem(@PathParam("woodItemId") Long woodItemId) {
        try {
            WoodItem item = woodInventoryService.retrieveWoodItem(woodItemId);
            return Response.ok(item).build();
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GET
    @Timed
    @Path("/checkout")
    public Response listCheckoutWoodItems() {
        try {
            List<WoodItem> inventory = woodInventoryService.retrieveCheckedOutItems();
            return Response.ok(inventory).build();
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GET
    @Timed
    @Path("/checkout/{woodItemId}")
    public Response checkoutWoodItemFromInventory(@PathParam("woodItemId") Long woodItemId) {
        try {
            woodInventoryService.checkoutWoodItem(woodItemId);
            return Response.ok().build();
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }


    @POST
    @Timed
    @Path("/checkin")
    public Response checkinWoodItemFromInventory(WoodItem woodItem) {
        try {
            logger.info("Checking in WoodItem with ID " + woodItem.getWoodInventoryId());
            return Response.ok(woodInventoryService.checkinWoodItem(woodItem)).build();
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
