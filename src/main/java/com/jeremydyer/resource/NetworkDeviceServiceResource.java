package com.jeremydyer.resource;

import com.codahale.metrics.annotation.Timed;
import com.jeremydyer.core.NetworkDeviceService;
import com.jeremydyer.service.GPIOService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * User: Jeremy Dyer
 * Date: 3/26/14
 * Time: 1:11 PM
 */
@Path("/location/{locationId}/device/{deviceId}/service")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class NetworkDeviceServiceResource {

    private static final Logger logger = LoggerFactory.getLogger(NetworkDeviceServiceResource.class);

    private GPIOService gpioService;

    public NetworkDeviceServiceResource(GPIOService gpioService) {
        this.gpioService = gpioService;
    }


    @GET
    @Timed
    public Response retrieveAllDeviceServices(@PathParam("locationId") Long locationId, @PathParam("deviceId") Long deviceId) {
        //TODO: Enable authentication and capture the user id here to pass to this method. currently just all
        // locations will be returned.
        List<NetworkDeviceService> services = null;
        try {
            services = gpioService.retrieveNetworkDeviceServiceForDeviceAndUser(null, deviceId, -1L);
            return Response.ok(services).build();
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GET
    @Timed
    @Path("/{serviceId}")
    public Response retrieveDeviceServiceOnDevice(@PathParam("locationId") Long locationId,
                                       @PathParam("deviceId") Long deviceId,
                                       @PathParam("serviceId") Long serviceId) {
        //TODO: Enable authentication and capture the user id here to pass to this method. currently just all
        // locations will be returned.
        List<NetworkDeviceService> services = null;

        try {
            services = gpioService.retrieveNetworkDeviceServiceForDeviceAndUser(serviceId, deviceId, -1L);

            if (services != null && services.size() == 1) {
                return Response.ok(services.get(0)).build();
            } else {
                //Couldn't find that NetworkDeviceService. Return 404.
                logger.info("No NetworkDeviceService found with Id " + serviceId);
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
