package com.tradeblancco.controller;

import com.tradeblancco.model.Credential;
import com.tradeblancco.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@WebServlet(name = "SignupServlet", value = "/SignupServlet")
public class SignupServlet extends HttpServlet {

    private User user;
    private String username;
    private String password;

    Path fileUsernamePath;
    Path fileUserinfoPath;

    BufferedReader in_credentials;
    BufferedReader in_userinfo;

    BufferedWriter out_credentials = null;

    Set<Credential> credentialList;
    List<User> users;

    public void init() throws ServletException {
        super.init();

        this.username = "no value provided";
        this.password = "no value provided";
        this.user = null;

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

        this.credentialList = new HashSet<>();
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

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        username = request.getParameter("username");
        password = request.getParameter("password");


        Optional<Credential> tempCredential;
        if(    (tempCredential =  credentialList.stream().filter(credential -> credential.getUsername().equals(username)).findAny()).isPresent() )
        {
            //DIspatch to Landing Page | Request a new Username because Username Exists

            request.setAttribute("errormessage2","Username Exists | Enter Another");
            RequestDispatcher view = request.getRequestDispatcher("index.jsp");
            view.forward(request,response);
        }
        else
        {

            try {
                out_credentials = Files.newBufferedWriter(this.fileUsernamePath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            //Create a new User | Dispatch to Create Profile Page | Store the Credentials | Create a Session

            Credential credential = new Credential(username, password);
            this.credentialList.add(credential);

            credentialList.forEach(credential1 -> {
                try {
                    out_credentials.append(credential1.getUsername()+","+credential1.getPassword()+"\n");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

            out_credentials.flush();
            //out_credentials.close();

            HttpSession session = request.getSession(true);
            session.setAttribute("usercredential",credential);

            RequestDispatcher view = request.getRequestDispatcher("updateprofile.jsp");
            view.forward(request,response);

        }



    }
}
