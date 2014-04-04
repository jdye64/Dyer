package com.jeremydyer.resource.gpio;

import com.jeremydyer.core.power.PowerOutlet;
import com.jeremydyer.dao.power.PowerOutletDao;
import com.jeremydyer.resource.ResourceBase2;
import com.jeremydyer.service.power.PowerService;
import com.makeandbuild.persistence.ObjectNotFoundException;
import com.makeandbuild.persistence.jdbc.BaseDao;
import com.yammer.metrics.annotation.Timed;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

/**
 * User: Jeremy Dyer
 * Date: 4/4/14
 * Time: 10:33 AM
 */
@Path("/power")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Service
public class PowerOutletResource
        extends ResourceBase2<PowerOutlet, Long> {

    private static final Logger logger = LoggerFactory.getLogger(PowerOutletResource.class);

    @Autowired
    private PowerOutletDao powerOutletDao;

    @Autowired
    private PowerService powerService;

    private ObjectMapper objectMapper;

    public PowerOutletResource() {
        super(PowerOutlet.class);
    }


    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Timed
    @Override
    public Response create(String json) {
        try{
            PowerOutlet outlet = getObjectMapper().readValue(json, PowerOutlet.class);
            outlet = getDao().create(outlet);

            //Now that a new outlet has been created we want to send the GPIO state information to the RPi
            this.powerService.setRPiPin(outlet);

            return Response.ok().entity(getObjectMapper().writeValueAsString(outlet)).build();
        }catch(ObjectNotFoundException e){
            return Response.status(400).build();
        }catch(Exception e){
            logger.warn("could not create resource with payload "+json, e);
            throw new RestClientException("unable to create resource");
        }
    }

    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    @Timed
    @Override
    public Response update(String json) {
        try{
            ObjectMapper mapper = new ObjectMapper();
            PowerOutlet outlet = mapper.readValue(json, PowerOutlet.class);
            //PowerOutlet outlet = getObjectMapper().readValue(json, PowerOutlet.class);
            outlet = getDao().update(outlet);

            //Now that the power outlet has been updated we want to send the GPIO state information to the RPi
            this.powerService.setRPiPin(outlet);

            return Response.ok().entity(getObjectMapper().writeValueAsString(outlet)).build();
        } catch(ObjectNotFoundException e){
            return Response.status(400).build();
        } catch(Exception e){
            logger.warn("could not update resource with payload " + json, e);
            throw new RestClientException("unable to create resource");
        }
    }


    @Override
    protected BaseDao<PowerOutlet, Long> getDao() {
        return powerOutletDao;
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