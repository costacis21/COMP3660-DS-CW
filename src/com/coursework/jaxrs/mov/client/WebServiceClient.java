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
    static final String REST_URI = "http://localhost:9997/cw";
    static final String MOV_PATH = "mov/";//genre-id param 3
    static final String GENRE_ID_PATH = "genreID/"; //movie-title param 1
    static final String GENRE_PATH = "genre/";//genre_name param 2

    public static void main(String[] args) {



        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        WebResource service = client.resource(REST_URI);
        WebResource getGenreNameService = service.path(GENRE_ID_PATH).queryParam("movie-title","John Wick");
        WebResource getGenreIDService = service.path(GENRE_PATH).queryParam("genre_name","Action");
        WebResource getMovSugService = service.path(MOV_PATH).queryParam("genre_ID","28"); //28=action

        long startT,endT;

        System.out.println("Genre Name: t+ " + getResponseAsHTML(getGenreNameService));
        for(int i=0; i<5;i++){
            startT = System.nanoTime();
            getResponseAsHTML(getGenreNameService);
            endT = System.nanoTime();
            System.out.println(endT - startT);
        }
        System.out.println("---------------------------------------------------");

        System.out.println("ID: " + getOutputAsText(getGenreIDService));
        for(int i=0; i<5;i++){
            startT = System.nanoTime();
            getOutputAsText(getGenreIDService);
            endT = System.nanoTime();
            System.out.println(endT - startT);
        }
        System.out.println("---------------------------------------------------");


        System.out.println("Movie suggestion: " + getResponseAsHTML(getMovSugService));
        for(int i=0; i<5;i++){
            startT = System.nanoTime();
            getResponseAsHTML(getMovSugService);
            endT = System.nanoTime();
            System.out.println(endT - startT);
        }
        System.out.println("---------------------------------------------------");

    }

    private static String getResponseAsHTML(WebResource service) {
        return service.accept(MediaType.TEXT_HTML).get(ClientResponse.class).toString();
    }


    private static String getOutputAsText(WebResource service) {
        return service.accept(MediaType.TEXT_PLAIN).get(String.class);
    }

    private static String getResponseAsEverything(WebResource service){
        return service.accept(MediaType.WILDCARD).get(ClientResponse.class).toString();
    }
}