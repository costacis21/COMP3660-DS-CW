package com.coursework.jaxrs.mov;


import java.io.IOException;
import java.net.URI;
import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.net.httpserver.HttpServer;




public class WebService1StartUp {

    static final String BASE_URI = "http://localhost:9999/cw/";
    URI uri = URI.create(BASE_URI);

    public static void main(String[] args) {
        try {
            HttpServer server = HttpServerFactory.create(BASE_URI);
            server.start();
            System.out.println("Press Enter to stop the server. ");
            System.in.read();
            server.stop(0);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
