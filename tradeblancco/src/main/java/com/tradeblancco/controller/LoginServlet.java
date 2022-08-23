package com.tradeblancco.controller;

import com.tradeblancco.model.Credential;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {

    private String username;
    private String password;

    Path fileUsernamePath;

    BufferedReader in_credentials;

    List<Credential> credentialList;

    @Override
    public void init() throws ServletException {
        super.init();
        this.username = "no value provided";
        this.password = "no value provided";

        Path p1 = Paths.get("D:\\SOftware Dev and Training Material\\Blancco\\tradeblancco\\src\\main\\resources\\data\\credentials.csv");
        String pathString = p1.toString();

        this.fileUsernamePath = p1.toAbsolutePath();

        try {
            in_credentials = Files.newBufferedReader(this.fileUsernamePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.credentialList = new ArrayList<>();

        try {
            this.parseCredentials();
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
           if(tempCredential.get().getPassword().equals(password))
           {
               // Dispatch the User to the dashboard.jsp
           }

        }



    }
}
