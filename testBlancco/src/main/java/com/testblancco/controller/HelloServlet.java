package com.testblancco.controller;

import java.io.*;
import javax.servlet.ServletInputStream;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello Blancco!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {




        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");

        // 10,000 more out.println()

        out.flush();
        out.close();
    }

    public void destroy() {
    }
}