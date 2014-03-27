package com.jeremydyer.resource.gpio;

import com.codahale.metrics.annotation.Timed;
import com.jeremydyer.service.GPIOService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * User: Jeremy Dyer
 * Date: 3/27/14
 * Time: 12:34 PM
 */
@Path("/gpio")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GPIOResource {

    private static final Logger logger = LoggerFactory.getLogger(GPIOResource.class);

    private GPIOService gpioService;

    public GPIOResource(GPIOService gpioService) {
        this.gpioService = gpioService;
    }

    @GET
    @Timed
    public Response retrieveAllNetworkLocations() {

    }
}
