package com.tradeblancco.controller;

import com.tradeblancco.model.Credential;
import com.tradeblancco.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@WebServlet(name = "UpdateProfileServlet", value = "/UpdateProfileServlet")
public class UpdateProfileServlet extends HttpServlet { //  Servlets should be Stateless (should not have any data members related to business logic )


    Path fileUserinfoPath = null;

    BufferedWriter out_userinfo = null;




    @Override
    public void init() throws ServletException {
        super.init();

        Path p2 = Paths.get("D:\\SOftware Dev and Training Material\\Blancco\\tradeblancco\\src\\main\\resources\\data\\userinfo.csv");
        //String pathString2 = p2.toString();
        this.fileUserinfoPath = p2.toAbsolutePath();

    }

    public synchronized Set<User> parseUserInfo() throws IOException {

        Set<User> tempUserSet = new HashSet<>();

        BufferedReader in_userinfo = null;

        try {
            in_userinfo = Files.newBufferedReader(this.fileUserinfoPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String line;
        while ( (line = in_userinfo.readLine()) != null)
        {

            String[] tempUserInfoString = line.split(",");
           tempUserSet.add(new User(tempUserInfoString[0], tempUserInfoString[1],tempUserInfoString[2],tempUserInfoString[3]));

        }

        return tempUserSet;

    }

    public synchronized void writeUserInfo(Set<User> tempUserSet) throws IOException {


        try {
            out_userinfo = Files.newBufferedWriter(this.fileUserinfoPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        tempUserSet.forEach(user -> {
            try {
                out_userinfo.append(user.getUsername()+","+user.getFullname()+","+user.getPhonenumber()+","+user.getType()+"\n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        out_userinfo.flush();
        //out_credentials.close();


    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = ((Credential)request.getSession(false).getAttribute("usercredential")).getUsername();
        String fullname = request.getParameter("fullname");
        String phonenumber = request.getParameter("phonenumber");
        String type = request.getParameter("type");

        User userinfo = new User(username,fullname,phonenumber,type);

        Set<User> userSet = parseUserInfo();
        userSet.add(userinfo);

        writeUserInfo(userSet);

        HttpSession session =  request.getSession(false); // get the session object reference for this user | create new session object if one doesn't exist already

        session.setAttribute("userinfo",userinfo);
        //session.setAttribute("usercredential",tempCredential);

        if( userinfo.getType().equals("buyer"))
        {
            RequestDispatcher view = request.getRequestDispatcher("buyerdashboard.jsp");
            view.forward(request,response);
        }
        else if(userinfo.getType().equals("seller"))
        {
            RequestDispatcher view = request.getRequestDispatcher("sellerdashboard.jsp");
            view.forward(request,response);

        }
        else if(  userinfo.getType().equals("admin")  )
        {
            RequestDispatcher view = request.getRequestDispatcher("dashboard.jsp");
            view.forward(request,response);
        }



    }
}
