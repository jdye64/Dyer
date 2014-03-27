package com.jeremydyer.resource;

import com.codahale.metrics.annotation.Timed;
import com.jeremydyer.core.NetworkDevice;
import com.jeremydyer.core.NetworkDeviceService;
import com.jeremydyer.core.dto.NetworkDeviceDTO;
import com.jeremydyer.service.GPIOService;
import com.jeremydyer.service.NetworkService;
import org.apache.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * User: Jeremy Dyer
 * Date: 3/25/14
 * Time: 2:49 PM
 */
@Path("/device")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class NetworkDeviceResource {

    private static final Logger logger = Logger.getLogger(NetworkDeviceResource.class);

    private NetworkService networkService;

    public NetworkDeviceResource(NetworkService networkService) {
        this.networkService = networkService;
    }


    @GET
    @Timed
    public Response retrieveNetworkDevices() {
        //TODO: Enable authentication and capture the user id here to pass to this method. currently just all
        // locations will be returned.
        List<NetworkDevice> locations = null;
        try {
            locations = networkService.retrieveNetworkDeviceForUserAtLocation(null, -1L);
            logger.info(locations.size() + " devices");
            return Response.ok(locations).build();
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GET
    @Timed
    @Path("/{deviceId}")
    public Response retrieveNetworkDevice(@PathParam("deviceId") Long deviceId) {
        //TODO: Enable authentication and capture the user id here to pass to this method. currently just all
        // locations will be returned.
        List<NetworkDevice> loc = null;

        try {
            loc = networkService.retrieveNetworkDeviceForUserAtLocation(deviceId, -1L);

            if (loc != null && loc.size() == 1) {
                NetworkDevice specificDevice = loc.get(0);
                List<NetworkDeviceService> services = networkService.retrieveNetworkDeviceServiceForUser(null, -1L);

                NetworkDeviceDTO dto = new NetworkDeviceDTO(specificDevice, services);
                return Response.ok(dto).build();
            } else {
                //Couldn't find that NetworkLocation. Return 404.
                logger.info("No NetworkDevice found with Id " + deviceId + " at location");
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

}
