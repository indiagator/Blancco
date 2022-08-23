package com.tradeblancco.controller;

import java.util.ArrayList;
import java.util.List;
import com.tradeblancco.model.Buyer;
import com.tradeblancco.repositories.DbConnection;

import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet
{ // Model + Controller --- > Serves Views



    @Override
    public void init()
    {

    }

    @Override
    public void destroy()
    {

    }

    private String message;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        response.setContentType("text/html");

        message = "this is from the get method body";

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");

        out.flush();
        out.close();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        response.setContentType("text/html");


        message = "this is from the Post method body";

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");

            out.println("<h1>" + message + "</h1>");

        out.println("</body></html>");

        out.flush();
        out.close();
    }



}