package com.jeremydyer;

import com.jeremydyer.resource.NetworkDeviceServiceResource;
import com.jeremydyer.resource.NetworkServiceCommandResource;
import com.jeremydyer.resource.NetworkDeviceResource;
import com.jeremydyer.resource.NetworkLocationResource;
import com.jeremydyer.service.GPIOService;
import com.jeremydyer.service.impl.GPIOServiceInMemory;
import com.sun.jersey.api.client.Client;
import io.dropwizard.Application;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;


/**
 * User: Jeremy Dyer
 * Date: 3/25/14
 * Time: 2:43 PM
 */
public class DyerService
    extends Application<DyerConfiguration> {

    public static void main(String[] args) throws Exception {
        new DyerService().run(args);
    }

    @Override
    public String getName() {
        return "Dyer";
    }

    @Override
    public void initialize(Bootstrap<DyerConfiguration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(DyerConfiguration configuration,
                    Environment environment) {
        GPIOService gpioService = new GPIOServiceInMemory();

        final NetworkLocationResource gpioLocationResource = new NetworkLocationResource(gpioService);
        final NetworkDeviceResource gpioResource = new NetworkDeviceResource(gpioService);
        final NetworkDeviceServiceResource networkDeviceServiceResource = new NetworkDeviceServiceResource
                (gpioService);
        final NetworkServiceCommandResource gpioCommandResource = new NetworkServiceCommandResource(gpioService);

        //Register the dropwizard resources with jersey
        environment.jersey().register(gpioLocationResource);
        environment.jersey().register(gpioResource);
        environment.jersey().register(gpioCommandResource);
        environment.jersey().register(networkDeviceServiceResource);

    }

}
