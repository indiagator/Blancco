package com.tradeblancco.controller;

import com.tradeblancco.model.Credential;
import com.tradeblancco.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {

    private Optional<User> user;
    private String username;
    private String password;

    private  HttpSession session;

    Path fileUsernamePath;
    Path fileUserinfoPath;

    BufferedReader in_credentials;
    BufferedReader in_userinfo;

    List<Credential> credentialList;
    List<User> users;

    @Override
    public void init() throws ServletException {
        super.init();

        this.username = "no value provided";
        this.password = "no value provided";
        this.user = null;
        this.session = null;

        Path p1 = Paths.get("D:\\SOftware Dev and Training Material\\Blancco\\tradeblancco\\src\\main\\resources\\data\\credentials.csv");
        //String pathString = p1.toString();

        this.fileUsernamePath = p1.toAbsolutePath();


        Path p2 = Paths.get("D:\\SOftware Dev and Training Material\\Blancco\\tradeblancco\\src\\main\\resources\\data\\userinfo.csv");
        //String pathString2 = p2.toString();

        this.fileUserinfoPath = p2.toAbsolutePath();

        try {
            in_credentials = Files.newBufferedReader(this.fileUsernamePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            in_userinfo = Files.newBufferedReader(this.fileUserinfoPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.credentialList = new ArrayList<>();
        this.users = new ArrayList<>();

        try {
            this.parseCredentials();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            this.parseUserInfo();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void parseCredentials() throws IOException {


        String line;
        while ( (line = this.in_credentials.readLine()) != null)
        {

            String[] tempCredentialsString = line.split(",");
            this.credentialList.add(new Credential(tempCredentialsString[0], tempCredentialsString[1]));

        }

    }

    public void parseUserInfo() throws IOException {


        String line;
        while ( (line = this.in_userinfo.readLine()) != null)
        {

            String[] tempUserInfoString = line.split(",");
            this.users.add(new User(tempUserInfoString[0], tempUserInfoString[1],tempUserInfoString[2],tempUserInfoString[3]));

        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String filterParam = (String) request.getAttribute("filterParam");

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println(filterParam);

        out.flush();
        out.close();

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        username = request.getParameter("username");
        password = request.getParameter("password");

        Optional<Credential> tempCredential;
        if(    (tempCredential =  credentialList.stream().filter(credential -> credential.getUsername().equals(username)).findAny()).isPresent() )
        {
           if(tempCredential.get().getPassword().equals(password))
           {
               // User Authenticated |  Dispatch the User to the dashboard.jsp

               // tempUser =  users.stream().filter(user -> user.getUsername().equals(username)).findAny() ;

               if(   (this.user = (users.stream().filter(user -> user.getUsername().equals(username)).findAny()) ).isPresent()  )
               {

                   //user = (users.stream().filter(user -> user.getUsername().equals(username)).findAny()).get();
                   //request.setAttribute("userinfo",user);

                  this.session =  request.getSession(true); // get the session object reference for this user | create new session object if one doesn't exist already

                   session.setAttribute("userinfo",user.get());
                   session.setAttribute("usercredential",tempCredential);

                   if( user.get().getType().equals("buyer"))
                   {
                       RequestDispatcher view = request.getRequestDispatcher("buyerdashboard.jsp");
                       view.forward(request,response);
                   }
                   else if(user.get().getType().equals("seller"))
                   {
                       RequestDispatcher view = request.getRequestDispatcher("sellerdashboard.jsp");
                       view.forward(request,response);

                   }
                   else if(   user.get().getType().equals("admin")  )
                   {
                       RequestDispatcher view = request.getRequestDispatcher("dashboard.jsp");
                       view.forward(request,response);
                   }

               }
               else
               {
                   //Dispatch to Update Profile jsp after setting session info with user credentials
                    this.session = request.getSession(true); // retreive the session object
                    session.setAttribute("usercredential",tempCredential.get());
                    session.setAttribute("userinfo",null);


                    RequestDispatcher view = request.getRequestDispatcher("updateprofile.jsp");
                    view.forward(request,response);

               }


           }
           else
           { // If username is valid but password is incorrect | Dispatch the User back to the Landing Page

               request.setAttribute("errormessage","Incorrect Password");

               RequestDispatcher view = request.getRequestDispatcher("index.jsp");
               view.forward(request,response);

           }

        }
        else
        {
            // If username is invalid | Dispatch the User back to the Landing Page

            request.setAttribute("errormessage","Incorrect Username");
            RequestDispatcher view = request.getRequestDispatcher("index.jsp");
            view.forward(request,response);


        }



    }
}
