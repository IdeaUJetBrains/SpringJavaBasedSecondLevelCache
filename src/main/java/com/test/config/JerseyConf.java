package com.test.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * Created by ruynatf on 29/06/2017.
 */
@ApplicationPath("rest")
public class JerseyConf extends ResourceConfig {

    public JerseyConf() {
        packages("com.test.rest");
        register(MultiPartFeature.class);
        register(JacksonFeature.class);
    }
}
