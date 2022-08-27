package com.testblancco.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Calculator", value = "/calculator")
public class Calculator extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {





         RequestDispatcher view = request.getRequestDispatcher("calculator.jsp");
        view.forward(request, response);




    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String num1String = request.getParameter("num1");
        String num2String = request.getParameter("num2");

        int num1 = Integer.valueOf(num1String).intValue();
        int num2 = Integer.valueOf(num2String).intValue();


        float result = 0.0f;

        String operation = getServletConfig().getInitParameter("defaultOperation");

        if(request.getParameter("operation").equals("sum"))
        {
            result = add(num1,num2);

        }
        else if (request.getParameter("operation").equals("minus"))
        {

            result = subtract(num1,num2);

        }
        else if (request.getParameter("operation").equals("mult"))
        {

            result = multiply(num1,num2);
        }
        else if (request.getParameter("operation").equals("div"))
        {
            result = divide(num1, num2);

        }

        request.setAttribute("result", new Float(result)); // An Attribute is created which is a Key Value Record


        RequestDispatcher view = request.getRequestDispatcher("calculator.jsp");
        view.forward(request, response);






    }

    public int add(int x, int y)
    {
        return x + y;
    }

    public int subtract(int x, int y)
    {
        return x - y;
    }

    public int multiply(int x, int y)
    {
        return x * y;
    }

    public int divide(int x, int y)
    {
        return x / y;
    }

}
