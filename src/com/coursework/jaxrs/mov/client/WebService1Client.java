package com.coursework.jaxrs.mov.client;


import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.text.*;

public class WebService1Client extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html";

    /** Process the HTTP Get request */
    public void doGet(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {
        response.setContentType(CONTENT_TYPE);
        PrintWriter out = response.getWriter();
        out.println("<h3>Choose favorite actor</h3>");
        out.println("<form method=\"GET\" action=" +
                "/move/actor>");
        out.println("Locale <select size=\"1\" name=\"actor\">");
        out.println("<option>yolo</option>");

        out.println("</select>");



        out.println("<p><input type=\"submit\" value=\"Submit\" >");
        out.println("<input type=\"reset\" value=\"Reset\"></p>");
        out.println("</form>");
        out.close(); // Close stream
    }



}
