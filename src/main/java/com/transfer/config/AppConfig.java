package com.transfer.config;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("resources")
class AppConfig extends ResourceConfig {

    AppConfig() {
        packages("com.transfer");
        register(JacksonFeature.class);
    }
}