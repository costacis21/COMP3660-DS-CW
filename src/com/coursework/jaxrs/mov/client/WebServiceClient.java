package com.coursework.jaxrs.mov.client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import com.sun.jersey.api.client.*;

import javax.ws.rs.core.MediaType;
import java.nio.channels.AcceptPendingException;

public class WebServiceClient {
    static final String REST_URI = "http://localhost:9999/cw";
    static final String ACTOR_PATH = "mov/";
    static final String GENRE_PATH = "genre/";
    static final String SUG_PATH = "sug/";

    public static void main(String[] args) {



        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        WebResource service = client.resource(REST_URI);

        WebResource actorService = service.path(ACTOR_PATH).queryParam("actor","Pellos1");
        System.out.println("Add Response: " + getResponse(actorService));
        System.out.println("Add Output as XML: " + getOutputAsXML(actorService));
        System.out.println("Add Output as Text: " + getOutputAsText(actorService));
        System.out.println("---------------------------------------------------");

        WebResource genreService = service.path(GENRE_PATH).queryParam("prefgenre","PellaErga");
        System.out.println("Sub Response: " + getResponse(genreService));
        System.out.println("Sub Output as XML: " + getOutputAsXML(genreService));
        System.out.println("---------------------------------------------------");

//        WebResource sugService = service.path(SUG_PATH).path("PelloErgo");
//        System.out.println("Mult Response: " + getResponse(sugService));
//        System.out.println("Mult Output as XML: " + getOutputAsXML(sugService));
//        System.out.println("---------------------------------------------------");

    }

    private static String getResponse(WebResource service) {
        return service.accept(MediaType.TEXT_XML).get(ClientResponse.class).toString();
    }

    private static String getOutputAsXML(WebResource service) {
        return service.accept(MediaType.TEXT_XML).get(String.class);
    }

    private static String getOutputAsText(WebResource service) {
        return service.accept(MediaType.TEXT_PLAIN).get(String.class);
    }
}