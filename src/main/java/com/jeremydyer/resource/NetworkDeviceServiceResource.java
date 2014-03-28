package com.jeremydyer.resource;

import com.jeremydyer.core.NetworkDeviceService;
import com.jeremydyer.core.NetworkDeviceServiceCommand;
import com.jeremydyer.core.dto.NetworkDeviceServiceDTO;
import com.jeremydyer.service.NetworkService;
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
 * Date: 3/26/14
 * Time: 1:11 PM
 */
@Path("/service")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Service
public class    NetworkDeviceServiceResource {

    private static final Logger logger = LoggerFactory.getLogger(NetworkDeviceServiceResource.class);

    @Autowired
    private NetworkService networkService;

    @GET
    @Timed
    @Path("/{serviceId}")
    public Response retrieveDeviceServiceOnDevice(@PathParam("serviceId") Long serviceId) {
        try {
            NetworkDeviceService service = networkService.serviceById(serviceId);
            List<NetworkDeviceServiceCommand> commandList = networkService.commandsForService(serviceId);
            NetworkDeviceServiceDTO dto = new NetworkDeviceServiceDTO(service, commandList);
            return Response.ok(dto).build();
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
