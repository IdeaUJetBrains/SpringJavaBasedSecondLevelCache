package com.test.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

import com.test.entities.LogEntity;
import com.test.repositories.LogRepository;

/**
 * Created by ruynatf on 29/06/2017.
 */
@Component
@Path("/logs")
public class LogRest {

    @Inject
    private LogRepository logRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        try {
            List<LogEntity> all = logRepository.findAll();
            return Response.ok(all).build();
        } catch (Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
