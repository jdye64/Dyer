package com.jeremydyer.resource.network;

import com.jeremydyer.core.network.NetworkDevice;
import com.jeremydyer.core.network.NetworkDeviceService;
import com.jeremydyer.core.network.dto.NetworkDeviceDTO;
import com.jeremydyer.service.network.NetworkService;
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
 * Date: 3/25/14
 * Time: 2:49 PM
 */
@Path("/device")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Service
public class NetworkDeviceResource {

    private static final Logger logger = Logger.getLogger(NetworkDeviceResource.class);

    @Autowired
    private NetworkService networkService;

    @GET
    @Timed
    public Response retrieveNetworkDevices() {
        try {
            List<NetworkDevice> devices = networkService.allDevicesForUser(-1L);
            logger.info(devices.size() + " devices");
            return Response.ok(devices).build();
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GET
    @Timed
    @Path("/{deviceId}")
    public Response retrieveNetworkDevice(@PathParam("deviceId") Long deviceId) {
        try {
            NetworkDevice device = networkService.deviceById(deviceId);
            List<NetworkDeviceService> services = networkService.servicesForDevice(deviceId);
            NetworkDeviceDTO dto = new NetworkDeviceDTO(device, services);
            return Response.ok(dto).build();
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

}
