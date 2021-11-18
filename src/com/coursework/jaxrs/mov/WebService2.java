package com.coursework.jaxrs.mov;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.Map;
import java.util.Random;


@Path("/genre")
public class WebService2 {
    JSONObject genres = new JSONObject();
    JSONParser parser = new JSONParser();
    String genresStr ="[\n" +
            "    {\n" +
            "      \"id\": 28,\n" +
            "      \"name\": \"Action\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 12,\n" +
            "      \"name\": \"Adventure\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 16,\n" +
            "      \"name\": \"Animation\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 35,\n" +
            "      \"name\": \"Comedy\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 80,\n" +
            "      \"name\": \"Crime\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 99,\n" +
            "      \"name\": \"Documentary\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 18,\n" +
            "      \"name\": \"Drama\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 10751,\n" +
            "      \"name\": \"Family\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 14,\n" +
            "      \"name\": \"Fantasy\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 36,\n" +
            "      \"name\": \"History\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 27,\n" +
            "      \"name\": \"Horror\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 10402,\n" +
            "      \"name\": \"Music\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 9648,\n" +
            "      \"name\": \"Mystery\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 10749,\n" +
            "      \"name\": \"Romance\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 878,\n" +
            "      \"name\": \"Science Fiction\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 10770,\n" +
            "      \"name\": \"TV Movie\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 53,\n" +
            "      \"name\": \"Thriller\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 10752,\n" +
            "      \"name\": \"War\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 37,\n" +
            "      \"name\": \"Western\"\n" +
            "    }\n" +
            "  ]";



    @GET
    @Path("/")
    @Produces(MediaType.TEXT_PLAIN)
    public String genreXML(@QueryParam("genre_name") String genreName) {
        try{
            Object jsonGenres = parser.parse(genresStr);
            JSONArray array = (JSONArray)jsonGenres;
            String genre;
            String id="0";
            for(int i=0;i<array.size();i++){
                Object jsonGenre = parser.parse(array.get(i).toString());
                JSONObject genreJson = (JSONObject)jsonGenre;
                genre = genreJson.get("name").toString();
                if(genre.equals(genreName)){
                    id = genreJson.get("id").toString();
                }

            }

            return id;


        }catch (ParseException e){
            e.printStackTrace();
        }
        return ("");
    }

}
