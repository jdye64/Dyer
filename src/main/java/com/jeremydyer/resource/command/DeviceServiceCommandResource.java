package com.jeremydyer.resource.command;

import com.jeremydyer.core.dto.NetworkServiceCommandResponseDTO;
import com.jeremydyer.service.CommandService;
import com.yammer.metrics.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
 * Created by jeremydyer on 3/27/14.
 */
@Path("/device/{deviceId}/service/{serviceId}/command")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Service
public class DeviceServiceCommandResource {

    private static final Logger logger = LoggerFactory.getLogger(DeviceServiceCommandResource.class);

    @Autowired
    private CommandService restCommandService;

    @POST
    @Timed
    @Path("/{commandId}")
    public Response retrieveAllServiceCommandsOnDevice(@PathParam("deviceId") Long deviceId,
                                                       @PathParam("serviceId") Long serviceId,
                                                       @PathParam("commandId") Long commandId) {
        logger.info("Executing command " + commandId + " for service " + serviceId + " on device " + deviceId);

        try {
            NetworkServiceCommandResponseDTO responseDTO = restCommandService.executeServiceCommandOnDevice(serviceId, commandId, deviceId);
            return Response.ok(responseDTO).build();
        } catch (Exception ex) {
            logger.error(null, ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
