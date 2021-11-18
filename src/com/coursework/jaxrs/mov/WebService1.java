package com.coursework.jaxrs.mov;


import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/mov")
public class WebService1 {
    final static String TMDB_POSTER_URI = "https://image.tmdb.org/t/p/w500/";
    final static String TMDB_INFO_URI = "https://www.themoviedb.org/movie/";
    final static String TMDB_URI = "https://api.themoviedb.org/3";
    final static String TMDB_DISCOVERY_URI = "/discover/movie";
    final static String api_key="bd3bccc81b8b90568f58a7d8d3299477";
    JSONObject genres = new JSONObject();
    JSONParser parser = new JSONParser();

    @GET
    @Path("/")
    @Produces({MediaType.TEXT_HTML,MediaType.WILDCARD})
    public String suggestMovies(@QueryParam("genre_ID") String genreID) throws ParseException {

        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        WebResource service = client.resource(TMDB_URI);

        WebResource tmdbSugService = service.path(TMDB_DISCOVERY_URI).queryParam("api_key",api_key).queryParam("with_genres", genreID);
        String JSONresponse = getOutputAsText(tmdbSugService);

        JSONObject  obj = (JSONObject) parser.parse(JSONresponse);
        JSONArray results  = (JSONArray) obj.get("results");
        String out,pic_url, title,id;
        out=pic_url=title=id="";
        out+="<div style= \" display: flex;\n" +
                "  justify-content: center;\n" +
                "  align-items: center;\n" +
                "  text-align: justify;\n" +
                "  min-height: 100vh;\">";
        for(int i=0;i<5;i++){
            JSONObject  resultObj = (JSONObject) parser.parse(results.get(i).toString());
            pic_url = resultObj.get("poster_path").toString();
            title = resultObj.get("original_title").toString();
            id = resultObj.get("id").toString();
            out+="<a href=\" " + TMDB_INFO_URI+ id +  " \" >\n" +
                    "         <img alt=\"Qries\" src=\" " + TMDB_POSTER_URI+ pic_url + " \" " +
                    "         width=400\" height=\"800\">\n" +
                    "      </a>";
        }
        out+="</div>";
        return out;
    }

    private static String getOutputAsText(WebResource service) {
        return service.accept(MediaType.TEXT_PLAIN).get(String.class);
    }

}
