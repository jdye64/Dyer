package com.jeremydyer.resource.network;

import com.jeremydyer.core.network.NetworkLocation;
import com.jeremydyer.dao.network.NetworkLocationDao;
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
public class NetworkLocationResource
        extends ResourceBase2<NetworkLocation, Long> {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(NetworkLocationResource.class);

    @Autowired
    private NetworkLocationDao networkDeviceServiceDao;

    private ObjectMapper objectMapper;

    public NetworkLocationResource() {
        super(NetworkLocation.class);
    }

    @Override
    protected BaseDao<NetworkLocation, Long> getDao() {
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