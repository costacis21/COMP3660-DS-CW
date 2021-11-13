package com.coursework.jaxrs.mov;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/mov")
public class WebService1 {

    @GET
    @Path("/")
    @Produces(MediaType.TEXT_PLAIN)
    public String actorPlain(@QueryParam("actor") String a) {
        return (a)  ;
    }


    @GET
    @Path("/")
    @Produces(MediaType.TEXT_XML)
    public String actorXML(@QueryParam("actor") String a) {
        return "<?xml version=\"1.0\"?>" + "<result>" +  (a) + "</result>";
    }

}
