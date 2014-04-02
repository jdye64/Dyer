package com.jeremydyer.resource.woodshop;

import com.jeremydyer.core.woodshop.WoodSpecies;
import com.jeremydyer.dao.woodshop.WoodSpeciesDao;
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
 * Time: 1:50 PM
 */
@Path("/wood/species")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Service
public class WoodSpeciesResource
        extends ResourceBase2<WoodSpecies, Long> {

    private static final Logger logger = LoggerFactory.getLogger(WoodSpeciesResource.class);

    @Autowired
    private WoodSpeciesDao woodSpeciesDao;

    private ObjectMapper objectMapper;

    public WoodSpeciesResource() {
        super(WoodSpecies.class);
    }

    @Override
    protected BaseDao<WoodSpecies, Long> getDao() {
        return woodSpeciesDao;
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
