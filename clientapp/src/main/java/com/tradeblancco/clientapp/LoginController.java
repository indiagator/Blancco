package com.tradeblancco.clientapp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@ComponentScan
@Controller
public class LoginController {

    @Autowired
    public WebApplicationContext webApplicationContext;

    @Autowired
    public CredentialRepository credentialRepository;

    @Autowired
    public UserinfoRepository userinfoRepository;

    @Autowired
    public HttpSession session;


    @PostMapping("/signup")
    public String signup(@RequestParam(name = "username") String username, @RequestParam(name = "password") String password, HttpServletRequest request, Model model) throws JsonProcessingException {

        Credential credential = new Credential();
        credential.setId(username);
        credential.setPassword(password);

        credentialRepository.save(credential);

        ObjectMapper objectMapper = new ObjectMapper();
        String credentialjson = objectMapper.writeValueAsString(credential);

        session.setAttribute("credential",credential);
        model.addAttribute("credentialjson",credentialjson);

        Credential testCredential  = objectMapper.readValue(credentialjson, Credential.class);

        return "updateprofile";
    }

    @PostMapping("/updateprofile")
    public String updateprofile(@RequestParam(name = "fullname")  String fullname, @RequestParam(name = "phonenumber", required = false) String phonenumber,@RequestParam(name = "type") String type, HttpServletRequest request)
    {

        Userinfo userinfo = new Userinfo();

        userinfo.setFullname(fullname);
        userinfo.setPhonenumber(phonenumber);
        userinfo.setType(type);
        //userinfo.setCredentials((Credential) session.getAttribute("credential"));
        userinfo.setId( ((Credential) session.getAttribute("credential")).getId());

        userinfoRepository.save(userinfo);


        session.setAttribute("userinfo",userinfo);

        return "dashboard";

    }


}
