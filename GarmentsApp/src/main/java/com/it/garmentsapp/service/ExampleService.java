package com.it.garmentsapp.service;

import com.it.garmentsapp.bean.GarmentsBean;
import com.it.garmentsapp.entity.Garmentmaster;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.EJB;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import java.math.BigInteger;
import java.util.List;

@Path("/api")
public class ExampleService {

    @EJB
    GarmentsBean bean;

    @GET
    @RolesAllowed("Owner")
    public Response get() {
        return Response.ok("Hello, world!").build();
    }

    @GET
    @Path("/getAllGarments")
    public Response getAllGarments() {
        List<Garmentmaster> list = bean.getAllGarments();
        return Response.ok(list).build();
    }

    @GET
    @Path("/findGarmentsByByCategory/{category}")
    @RolesAllowed("Owner")
    public Response findGarmentsByByCategory(@PathParam("category") String category) {
        List<Garmentmaster> list = bean.findGarmentsByByCategory(category);
        return Response.ok(list).build();
    }

    @GET
    @Path("/findGarmentsByPrice/{min}/{max}")
    @RolesAllowed("Owner")
    public Response findGarmentsByPrice(@PathParam("min") BigInteger min, @PathParam("max") BigInteger max) {
        List<Garmentmaster> list = bean.findGarmentsByPrice(min, max);
        return Response.ok(list).build();
    }
}
