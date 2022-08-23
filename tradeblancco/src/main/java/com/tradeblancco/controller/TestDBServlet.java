package com.tradeblancco.controller;

import com.tradeblancco.repositories.DbConnection;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet(name = "TestDBServlet", value = "/TestDBServlet")
public class TestDBServlet extends HttpServlet {


    private String message;
    private Connection dbconnection;

    @Override
    public void init()
    {
        message = "Hello Prateek! Doing Great?";
        dbconnection = (new DbConnection()).getDbconnection();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String query = "SELECT * FROM customer";
        Statement statement = null;
        ResultSet resultSet = null;

        String s1 ="";
        String s2 ="";

        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");



        try
        {
            statement = dbconnection.createStatement();
            resultSet = statement.executeQuery(query);

            while(resultSet.next())
            {
                s1 = (resultSet.getString(1));
                s2 = (resultSet.getString(2));

                out.println("<p>" + s1 + "</p>");
                out.println("<p>" + s2 + "</p>");

                // System.out.println("Welcome to Swiggy "+resultSet.getString(2)+"! Your current registered phonenumber is "+resultSet.getString(1));

            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }

        out.println("</body></html>");

        out.flush();
        out.close();

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
