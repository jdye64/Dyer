package com.jeremydyer;

import com.jeremydyer.resource.NetworkDeviceServiceResource;
import com.jeremydyer.resource.NetworkDeviceResource;
import com.jeremydyer.resource.NetworkLocationResource;
import com.jeremydyer.resource.gpio.GPIOResource;
import com.jeremydyer.service.GPIOService;
import com.jeremydyer.service.NetworkService;
import com.jeremydyer.service.impl.GPIOServiceInMemory;
import com.jeremydyer.service.impl.NetworkServiceInMemory;
import io.dropwizard.Application;
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
        NetworkService networkService = new NetworkServiceInMemory();
        GPIOService gpioService = new GPIOServiceInMemory();

        final NetworkLocationResource networkLocationResource = new NetworkLocationResource(networkService);
        final NetworkDeviceResource networkDeviceResource = new NetworkDeviceResource(networkService);
        final NetworkDeviceServiceResource networkDeviceServiceResource = new NetworkDeviceServiceResource
                (networkService);
        final GPIOResource gpioResource = new GPIOResource(gpioService);

        //Register the dropwizard resources with jersey
        environment.jersey().register(networkLocationResource);
        environment.jersey().register(networkDeviceResource);
        environment.jersey().register(networkDeviceServiceResource);
        environment.jersey().register(gpioResource);
    }

}
