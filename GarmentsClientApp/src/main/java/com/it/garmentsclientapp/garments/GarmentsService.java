/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.it.garmentsclientapp.garments;

import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import java.math.BigInteger;
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

/**
 *
 * @author Aditya Pathak R
 */
@RegisterRestClient(configKey = "clkey")
public interface GarmentsService {

    @GET
    @RolesAllowed("Owner")
    @ClientHeaderParam(name = "Authorization", value = "{getToken}")
    public Response get();

    @GET
    @Path("/getAllGarments")
    public Response getAllGarments();

    @GET
    @Path("/findGarmentsByByCategory/{category}")
    @RolesAllowed("Owner")
    @ClientHeaderParam(name = "Authorization", value = "{getToken}")
    public Response findGarmentsByByCategory(@PathParam("category") String category);

    @GET
    @Path("/findGarmentsByPrice/{min}/{max}")
    @RolesAllowed("Owner")
    @ClientHeaderParam(name = "Authorization", value = "{getToken}")
    public Response findGarmentsByPrice(@PathParam("min") BigInteger min, @PathParam("max") BigInteger max);

    default String getToken() {
        Config config = ConfigProvider.getConfig();
        String token = "Bearer " + config.getValue("jwt", String.class);
//        System.out.println("Token: " + token);
        return token;
    }
}
