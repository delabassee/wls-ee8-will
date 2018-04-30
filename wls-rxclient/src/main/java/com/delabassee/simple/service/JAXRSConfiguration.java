package com.delabassee.simple.service;

import javax.ws.rs.core.Application;
import javax.ws.rs.ext.Provider;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;

/**
 * Configures a JAX-RS endpoint.
 */
@ApplicationPath("hotel")
@Provider
public class JAXRSConfiguration extends Application {
    private Set<Object> singletons = new HashSet<>();

    public JAXRSConfiguration() {
        singletons.add(new HotelResource());
    }

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
}
