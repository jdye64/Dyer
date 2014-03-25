package com.jeremydyer.resource;

import com.codahale.metrics.annotation.Timed;
import com.jeremydyer.core.GPIODevice;
import org.apache.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * User: Jeremy Dyer
 * Date: 3/25/14
 * Time: 2:49 PM
 */
@Path("/gpio")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GPIOResource {

    private static final Logger logger = Logger.getLogger(GPIOResource.class);

    //In memory list of GPIO Raspberry PIs on the network.
    private List<GPIODevice> devices = null;


    private final String template;
    private final String defaultName;
    private final AtomicLong counter;

    public GPIOResource(String template, String defaultName) {
        this.template = template;
        this.defaultName = defaultName;
        this.counter = new AtomicLong();
        devices = new ArrayList<GPIODevice>();
    }


    @POST
    @Timed
    public String networkGPIODeviceHeartbeat(GPIODevice device) {
        System.out.println("Heartbeat from GPIO device");

        //Makes sure that the device is not already in the list
        boolean found = false;
        for (GPIODevice dev : devices) {
            if (dev.getIpaddress().equals(device.getIpaddress())) {
                found = true;
                break;
            }
        }

        if (!found) {
            devices.add(device);
            System.out.println("There are now " + devices.size() + " GPIODevices in memory list");
        }

        return "Looks good bro";
    }


    @GET
    @Timed
    public List<GPIODevice> getGPIODevices() {
        System.out.println("Retrieve GPIO Devices");
        return this.devices;
    }
}
