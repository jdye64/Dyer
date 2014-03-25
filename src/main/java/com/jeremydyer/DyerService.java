package com.jeremydyer;

import com.jeremydyer.resource.GPIOResource;
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
        final GPIOResource resource = new GPIOResource(
                configuration.getTemplate(),
                configuration.getDefaultName()
        );
        environment.jersey().register(resource);
    }

}
