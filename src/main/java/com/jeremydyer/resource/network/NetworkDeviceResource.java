package com.jeremydyer.resource.network;

import com.jeremydyer.core.network.NetworkDevice;
import com.jeremydyer.dao.network.NetworkDeviceDao;
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
 * Date: 3/25/14
 * Time: 2:49 PM
 */
@Path("/device")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Service
public class NetworkDeviceResource
        extends ResourceBase2<NetworkDevice, Long> {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(NetworkDeviceResource.class);

    @Autowired
    private NetworkDeviceDao networkDeviceDao;

    private ObjectMapper objectMapper;

    public NetworkDeviceResource() {
        super(NetworkDevice.class);
    }

    @Override
    protected BaseDao<NetworkDevice, Long> getDao() {
        return networkDeviceDao;
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