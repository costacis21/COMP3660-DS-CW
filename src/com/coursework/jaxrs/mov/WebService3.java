package com.coursework.jaxrs.mov;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.text.*;
import java.sql.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/genreID")
public class WebService3 {

  static final String REST_URI = "http://localhost:9997/cw";


  @GET
  @Path("/")
  @Produces(MediaType.TEXT_HTML)
  public String genreHTML(@QueryParam("movie-title") String a) throws IOException{

    return getGenreName(a);
  }


  public static final String propsFile = "src/com/coursework/jaxrs/mov/jdbc.properties";
  private static final String CONTENT_TYPE = "text/html";
  String[] movies = null;

//load genre name from db
  public String getGenreName(String m_name) throws  IOException {

    Connection database = null;

    try{
      database = getConnection(); //connect to database

      Statement statement = database.createStatement();
      ResultSet results = statement.executeQuery("SELECT genre FROM movies WHERE title=\""+ m_name+"\""); //get list of movies

      if(results.next()) {
        String genre = results.getString("genre");
        statement.close();


        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        WebResource service = client.resource(REST_URI);

        WebResource genreIDService = service.path("genre").queryParam("genre_name",genre);
        String genreID =getOutputAsText(genreIDService);
        WebResource tmdbSugService = service.path("mov").queryParam("genre_ID",genreID);

        return getOutputAsText(tmdbSugService);

      }

    }catch(SQLException e){
        e.printStackTrace();      //catch error
    }finally {
      if (database != null) {
        try {
          database.close();
        }
        catch (Exception error) {}
      }
    }
  return null;

  }   



  /**Establishes a connection to the database**/
  public static Connection getConnection() throws IOException, SQLException
  {
    // Load properties

    FileInputStream in = new FileInputStream(propsFile);
    Properties props = new Properties();
    props.load(in);

    // Define JDBC driver

    String drivers = props.getProperty("jdbc.drivers");
    if (drivers != null)
      System.setProperty("jdbc.drivers", drivers);
      // Setting standard system property jdbc.drivers
      // is an alternative to loading the driver manually
      // by calling Class.forName()

    // Obtain access parameters and use them to create connection

    String url = props.getProperty("jdbc.url");
    String user = props.getProperty("jdbc.user");
    String password = props.getProperty("jdbc.password");

    return DriverManager.getConnection(url, user, password);
  }




  private static String getOutputAsText(WebResource service) {
    return service.accept(MediaType.TEXT_PLAIN).get(String.class);
  }

}
