package ru.geekbrains.rest;

import ru.geekbrains.service.ProductRepresentation;

import javax.ejb.Local;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Local
@Path("/v1/product")
public interface ProductServiceRest {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<ProductRepresentation> findAll();

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    ProductRepresentation findById(@PathParam("id") Long id);

    @GET
    @Path("/byname")
    @Produces(MediaType.APPLICATION_JSON)
    ProductRepresentation findByName(@QueryParam("name") String name);

    @GET
    @Path("/bycategoryid/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    List<ProductRepresentation> findByCategoryId(@PathParam("id") Long id);

    @GET
    @Path("/count")
    @Produces(MediaType.APPLICATION_JSON)
    Long countAll();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    void insert(ProductRepresentation product) throws IllegalAccessException;

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    void update(ProductRepresentation product);

    @DELETE
    @Path("/{id}")
    void deleteById(@PathParam("id") Long id);

}
