package com.jeremydyer.resource.woodshop;

import com.jeremydyer.core.woodshop.WoodItem;
import com.jeremydyer.dao.woodshop.WoodItemDao;
import com.jeremydyer.resource.ResourceBase2;
import com.makeandbuild.persistence.jdbc.BaseDao;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * User: Jeremy Dyer
 * Date: 4/2/14
 * Time: 1:40 PM
 */
@Path("/wood")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Service
public class WoodItemResource
        extends ResourceBase2<WoodItem, Long> {

    private static final Logger logger = LoggerFactory.getLogger(WoodItemResource.class);

    @Autowired
    private WoodItemDao woodItemDao;

    private ObjectMapper objectMapper;

    public WoodItemResource() {
        super(WoodItem.class);
    }

    @Override
    protected BaseDao<WoodItem, Long> getDao() {
        return woodItemDao;
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
