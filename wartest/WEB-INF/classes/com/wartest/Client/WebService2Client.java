package com.wartest.Client;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.text.*;


public class WebService2Client extends HttpServlet {
  

  private static final String CONTENT_TYPE = "text/html";
  String[] movies = null;

  /** Process the HTTP Get request to get list of popular movies*/
  public void doGet(HttpServletRequest request, HttpServletResponse
      response) throws ServletException, IOException {

    ArrayList<String> movies = new ArrayList<String>();



    PrintWriter out = response.getWriter();
    
    try{
	File catalinaBase = new File( System.getProperty( "catalina.base" ) ).getAbsoluteFile();
	File moviesFile = new File( catalinaBase, "/webapps/wartest/WEB-INF/classes/com/wartest/Client/movies.txt" );
	BufferedReader in = new BufferedReader(new FileReader(moviesFile));
	while (true) {

		

	      String line = in.readLine();
	      if (line == null)
		break;
	      movies.add(line);

	    }


    in.close();

     
    }catch (Exception error) {
      error.printStackTrace();
    }

    //display list of movies
    response.setContentType(CONTENT_TYPE);
    out.println("<h3>Movie Recommender</h3>");
    out.println("<form method=\"GET\" action=" +
      "http://localhost:9997/cw/genreID>");
    out.println("Choose your favourite movie <select size=\"1\" name=\"movie-title\">");

    //display movie options in dropdown
    for (int i = 0; i < movies.size(); i++) {
      out.println("<option name=\"movie-title\" value=\"" + movies.get(i) +"\">" +
        movies.get(i)  + "</option>");
    }
    out.println("</select>");

    out.println("<p><input type=\"submit\" value=\"Submit\" >");
    out.println("</form>");
    out.close(); // Close stream
  }   

}




