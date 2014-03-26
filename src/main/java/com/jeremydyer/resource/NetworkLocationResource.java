package com.jeremydyer.resource;

import com.codahale.metrics.annotation.Timed;
import com.jeremydyer.core.NetworkLocation;
import com.jeremydyer.service.GPIOService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * REST service for the NetworkLocations.
 *
 * User: Jeremy Dyer
 * Date: 3/26/14
 * Time: 11:17 AM
 */
@Path("/location")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class NetworkLocationResource {

    private static final Logger logger = LoggerFactory.getLogger(NetworkLocationResource.class);

    private GPIOService gpioService;

    public NetworkLocationResource(GPIOService gpioService) {
        this.gpioService = gpioService;
    }

    @GET
    @Timed
    public Response retrieveAllNetworkLocations() {
        //TODO: Enable authentication and capture the user id here to pass to this method. currently just all
        // locations will be returned.
        List<NetworkLocation> locations = null;
        try {
            locations = gpioService.retrieveNetworkLocationsForUser(null, -1L);
            return Response.ok(locations).build();
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Timed
    @Path("/{locationId}")
    public Response getNetworkLocation(@PathParam("locationId") Long locationId) {
        //TODO: Enable authentication and capture the user id here to pass to this method. currently just all
        // locations will be returned.
        List<NetworkLocation> loc = null;

        try {
            loc = gpioService.retrieveNetworkLocationsForUser(locationId, -1L);

            if (loc != null && loc.size() == 1) {
                return Response.ok(loc.get(0)).build();
            } else {
                //Couldn't find that NetworkLocation. Return 404.
                logger.info("No NetworkLocation found with Id " + locationId);
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
