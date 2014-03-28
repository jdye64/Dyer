package com.jeremydyer.resource;

import com.jeremydyer.core.NetworkDevice;
import com.jeremydyer.core.NetworkLocation;
import com.jeremydyer.core.dto.NetworkLocationDTO;
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
 * REST service for the NetworkLocations.
 *
 * User: Jeremy Dyer
 * Date: 3/26/14
 * Time: 11:17 AM
 */
@Path("/location")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Service
public class NetworkLocationResource {

    private static final Logger logger = LoggerFactory.getLogger(NetworkLocationResource.class);

    @Autowired
    private NetworkService networkService;

    @GET
    @Timed
    public Response retrieveAllNetworkLocations() {
        List<NetworkLocation> locations = null;
        try {
            locations = networkService.networkLocationsForUser(-1L);
            return Response.ok(locations).build();
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GET
    @Timed
    @Path("/{locationId}")
    public Response getNetworkLocationWithDevices(@PathParam("locationId") Long locationId) {
        try {
            NetworkLocation loc = networkService.networkLocationById(locationId);
            List<NetworkDevice> locationDevices = networkService.devicesAtLocation(locationId);
            NetworkLocationDTO dto = new NetworkLocationDTO(loc, locationDevices);
            return Response.ok(dto).build();
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

}
