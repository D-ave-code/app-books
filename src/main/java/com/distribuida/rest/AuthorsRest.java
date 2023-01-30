package com.distribuida.rest;

import com.distribuida.db.Authors;
import com.distribuida.servicios.AuthorsRepository;
import com.distribuida.servicios.AuthorsRepositoryImpl;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
@ApplicationScoped
@Path("/authors")
public class AuthorsRest {
    @Inject
    AuthorsRepository authorsRepository;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Authors> findAll () {
        return Authors.listAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") Long id){
        Authors authors = authorsRepository.findById(id);
        //System.out.println(authors.getId());
        if(authors != null){
            return Response.ok(authors).build();
        }
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete (@PathParam("id") Long id){
       // authorsRepository.delete(id);
        try {
            authorsRepository.delete(id);
        }catch (Exception ex){
            return Response.noContent().build();
        }

        return Response.status((Response.Status.OK) ).build();
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(Authors b){

       b = authorsRepository.create(b);

        return Response.ok(b).status((Response.Status.CREATED)).build();
    }
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Authors update (Authors b,@PathParam("id") Long id){
        b.setId(id);
        return authorsRepository.update(b, id);
    }
}