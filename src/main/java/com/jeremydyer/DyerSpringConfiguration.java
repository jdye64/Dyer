package com.jeremydyer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.security.access.vote.RoleVoter;

/**
 * Created by jeremydyer on 3/27/14.
 */
@Configuration
@ImportResource({"classpath:spring/applicationContext*.xml"})
@ComponentScan(basePackageClasses = DyerSpringConfiguration.class)
public class DyerSpringConfiguration {

    @Bean
    public RoleVoter getRoleVoter() {
        RoleVoter roleVoter = new RoleVoter();
        roleVoter.setRolePrefix("ROLE _");
        return roleVoter;
    }
}