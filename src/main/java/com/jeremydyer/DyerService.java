package com.jeremydyer;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jeremydyer.spring.SpringContextLoaderListener;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.assets.AssetsBundle;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import com.yammer.dropwizard.tasks.Task;
import com.yammer.metrics.core.HealthCheck;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.ws.rs.Path;
import javax.ws.rs.ext.Provider;
import java.util.Map;


/**
 * User: Jeremy Dyer
 * Date: 3/25/14
 * Time: 2:43 PM
 */
public class DyerService
    extends Service<DyerConfiguration> {

    public static void main(String[] args) throws Exception {
        new DyerService().run(args);
    }

    @Override
    public void initialize(Bootstrap<DyerConfiguration> bootstrap) {
        bootstrap.setName("Dyer");
        bootstrap.getObjectMapperFactory().setSerializationInclusion(JsonInclude.Include.NON_NULL);

        //serve some HTML resources
        bootstrap.addBundle(new AssetsBundle("/assets/", "/dyer/"));
    }

    @Override
    public void run(DyerConfiguration configuration,
                    Environment environment) throws ClassNotFoundException {

        // nothing to do yet
        AnnotationConfigWebApplicationContext parent = new AnnotationConfigWebApplicationContext();
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();

        parent.refresh();
        parent.getBeanFactory().registerSingleton("configuration", configuration);
        parent.registerShutdownHook();
        parent.start();

        //the real main app context has a link to the parent context
        ctx.setParent(parent);
        ctx.register(DyerSpringConfiguration.class);
        ctx.refresh();
        ctx.registerShutdownHook();
        ctx.start();

        //now that Spring is started, let's get all the beans that matter into DropWizard

        //health checks
        Map<String, HealthCheck> healthChecks = ctx.getBeansOfType(HealthCheck.class);
        for(Map.Entry<String,HealthCheck> entry : healthChecks.entrySet()) {
            environment.addHealthCheck(entry.getValue());
        }

        //resources
        Map<String, Object> resources = ctx.getBeansWithAnnotation(Path.class);
        for(Map.Entry<String,Object> entry : resources.entrySet()) {
            environment.addResource(entry.getValue());
        }

        //tasks
        Map<String, Task> tasks = ctx.getBeansOfType(Task.class);
        for(Map.Entry<String,Task> entry : tasks.entrySet()) {
            environment.addTask(entry.getValue());
        }

        //JAX-RS providers
        Map<String, Object> providers = ctx.getBeansWithAnnotation(Provider.class);
        for(Map.Entry<String,Object> entry : providers.entrySet()) {
            environment.addProvider(entry.getValue());
        }

        //last, but not least, let's link Spring to the embedded Jetty in Dropwizard
        environment.addServletListeners(new SpringContextLoaderListener(ctx));

        //activate Spring Security filter
        environment.addFilter(DelegatingFilterProxy.class,"/*").setName("springSecurityFilterChain");

//        final DBIFactory factory = new DBIFactory();
//        final DBI jdbi = factory.build(environment, configuration.getDataSourceFactory(), "mysql");
//
//        //Creates the DAOs first since they will be injected into the service layers.
//        final NetworkLocationDao networkLocationDao = jdbi.onDemand(NetworkLocationDao.class);
//        final NetworkDeviceDao networkDeviceDao = jdbi.onDemand(NetworkDeviceDao.class);
//        final NetworkDeviceServiceDao networkDeviceServiceDao = jdbi.onDemand(NetworkDeviceServiceDao.class);
//        final NetworkDeviceServiceCommandDao networkDeviceServiceCommandDao = jdbi.onDemand(NetworkDeviceServiceCommandDao.class);
//
//        NetworkService networkService = new NetworkServiceImpl(networkLocationDao, networkDeviceDao, networkDeviceServiceDao);
//        CommandService restCommandService = new RESTCommandService(networkDeviceServiceCommandDao);
//
//        final NetworkLocationResource networkLocationResource = new NetworkLocationResource(networkService);
//        final NetworkDeviceResource networkDeviceResource = new NetworkDeviceResource(networkService);
//        final NetworkDeviceServiceResource networkDeviceServiceResource = new NetworkDeviceServiceResource
//                (networkService);
//        final DeviceServiceCommandResource deviceServiceCommandResource = new DeviceServiceCommandResource(restCommandService);
//
//        //Register the dropwizard resources with jersey
//        environment.jersey().register(networkLocationResource);
//        environment.jersey().register(networkDeviceResource);
//        environment.jersey().register(networkDeviceServiceResource);
//        environment.jersey().register(deviceServiceCommandResource);
    }

}
