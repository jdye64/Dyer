package com.jeremydyer.resource.network.command;

import com.jeremydyer.core.network.NetworkDeviceServiceCommand;
import com.jeremydyer.core.network.dto.NetworkServiceCommandResponseDTO;
import com.jeremydyer.dao.network.NetworkDeviceServiceCommandDao;
import com.jeremydyer.resource.ResourceBase2;
import com.jeremydyer.service.network.CommandService;
import com.makeandbuild.persistence.jdbc.BaseDao;
import com.yammer.metrics.annotation.Timed;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize;
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
@Path("/command")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Service
public class DeviceServiceCommandResource
        extends ResourceBase2<NetworkDeviceServiceCommand, Long> {

    private static final Logger logger = LoggerFactory.getLogger(DeviceServiceCommandResource.class);

    @Autowired
    private NetworkDeviceServiceCommandDao networkDeviceServiceCommandDao;

    @Autowired
    private CommandService restCommandService;

    private ObjectMapper objectMapper;


    public DeviceServiceCommandResource() {
        super(NetworkDeviceServiceCommand.class);
    }


    @Override
    protected BaseDao<NetworkDeviceServiceCommand, Long> getDao() {
        return networkDeviceServiceCommandDao;
    }

    @Override
    protected ObjectMapper getObjectMapper() {
        if (objectMapper == null){
            objectMapper = new ObjectMapper();
            objectMapper.getSerializationConfig().setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
            objectMapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);
        }
        return objectMapper;
    }


    @POST
    @Timed
    @Path("/device/{deviceId}/service/{serviceId}/command/{commandId}")
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
