package com.testblancco.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Locale;

@WebServlet(name = "BodyServlet", value = "/BodyServlet")
public class BodyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServletInputStream in = request.getInputStream();
        BufferedReader requestBody = new BufferedReader(new InputStreamReader(in));
        String requestBodyString = requestBody.readLine();

        Locale locale = request.getLocale();
        String country = locale.getCountry();

        String header = request.getHeader("User-Agent");

        String queryString = request.getQueryString();

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(requestBodyString+" Country: "+country+" Query String :"+queryString+" Header: User-Agent "+header);

        out.flush();
        out.close();

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        Integer filterParam = (Integer) request.getAttribute("filterParam");


        Integer intParam = Integer.valueOf(getServletConfig().getInitParameter("testParam"));


        Integer contextParam = Integer.valueOf(getServletContext().getInitParameter("testContextParam"));

        ServletInputStream in = request.getInputStream();
        BufferedReader requestBody = new BufferedReader(new InputStreamReader(in));
        String requestBodyString = requestBody.readLine();

        Locale locale = request.getLocale();
        String country = locale.getCountry();

        String header = request.getHeader("User-Agent");

        Enumeration<String> headerNames = request.getHeaderNames();

        String queryString = request.getQueryString();

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println(filterParam+"<- FilterParam"+contextParam+"<- ContextParam "+intParam+" <- Init Param"+requestBodyString+" Country: "+country+" Query String :"+queryString+" Header: User-Agent "+header);

        while (headerNames.hasMoreElements())
        {
            out.println(headerNames.nextElement());
        }

        out.flush();
        out.close();

    }
}
