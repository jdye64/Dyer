package com.jeremydyer.resource.network;

import com.jeremydyer.core.network.NetworkDeviceService;
import com.jeremydyer.dao.network.NetworkDeviceServiceDao;
import com.jeremydyer.resource.ResourceBase2;
import com.makeandbuild.persistence.jdbc.BaseDao;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * User: Jeremy Dyer
 * Date: 3/26/14
 * Time: 1:11 PM
 */
@Path("/service")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Service
public class NetworkDeviceServiceResource
        extends ResourceBase2<NetworkDeviceService, Long> {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(NetworkDeviceServiceResource.class);

    @Autowired
    private NetworkDeviceServiceDao networkDeviceServiceDao;

    private ObjectMapper objectMapper;

    public NetworkDeviceServiceResource() {
        super(NetworkDeviceService.class);
    }

    @Override
    protected BaseDao<NetworkDeviceService, Long> getDao() {
        return networkDeviceServiceDao;
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
}