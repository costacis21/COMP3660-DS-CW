package com.coursework.jaxrs.recMovie;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/recMovie")
public class WebService2 {

    @GET
    @Path("/")
    @Produces(MediaType.TEXT_PLAIN)
    public String moviesPlain(@QueryParam("movies") String a) {
        return (a)  ;
    }


    @GET
    @Path("/")
    @Produces(MediaType.TEXT_XML)
    public String moviesXML(@QueryParam("movies") String a) {
        return "<?xml version=\"1.0\"?>" + "<result>" +  (a) + "</result>";
    }

}
