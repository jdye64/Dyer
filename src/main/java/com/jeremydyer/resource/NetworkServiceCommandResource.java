package com.jeremydyer.resource;

import com.codahale.metrics.annotation.Timed;
import com.jeremydyer.core.NetworkDeviceServiceCommand;
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
 * Time: 9:48 AM
 */
@Path("/location/{locationId}/device/{deviceId}/service/{serviceId}/command")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class NetworkServiceCommandResource {

    private static final Logger logger = LoggerFactory.getLogger(NetworkServiceCommandResource.class);

    private GPIOService gpioService;

    public NetworkServiceCommandResource(GPIOService gpioService) {
        this.gpioService = gpioService;
    }

    @GET
    @Timed
    public Response retrieveAllCommandsForDeviceService(@PathParam("deviceId") Long deviceId, @PathParam("serviceId")
            Long serviceId) {
        //TODO: Enable authentication and capture the user id here to pass to this method. currently just all
        // locations will be returned.
        List<NetworkDeviceServiceCommand> serviceCommands = null;
        try {
            serviceCommands = gpioService.retrieveDeviceCommandsForDeviceServiceOnDeviceWithUser(null, serviceId,
                    deviceId, -1L);
            return Response.ok(serviceCommands).build();
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Timed
    @Path("/{commandId}")
    public Response getNetworkLocation(@PathParam("deviceId") Long deviceId, @PathParam("serviceId")
            Long serviceId, @PathParam("commandId") Long commandId) {
        //TODO: Enable authentication and capture the user id here to pass to this method. currently just all
        // locations will be returned.
        List<NetworkDeviceServiceCommand> serviceCommands = null;

        try {
            serviceCommands = gpioService.retrieveDeviceCommandsForDeviceServiceOnDeviceWithUser(commandId, serviceId,
                    deviceId, -1L);

            if (serviceCommands != null && serviceCommands.size() == 1) {
                return Response.ok(serviceCommands.get(0)).build();
            } else {
                //Couldn't find that NetworkLocation. Return 404.
                logger.info("No ServiceCommands found with Id " + commandId);
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
