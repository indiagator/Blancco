package com.codeaug.testblanccospring;


import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Controller
public class LandingController {

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired
    ServletContext servletContext;

    @Autowired
    WebApplicationContext context;

    @Autowired
    ApplicationContext ctx;

    @Autowired
    CustomerRepository customerRepository;

    @GetMapping("/landing")
    public String landing(Model model, HttpServletRequest request)
    {
        model.addAttribute("name", "blancco");
        model.addAttribute("appName", customerRepository.findById("9718406807").get().getName() );
        request.getSession(true).setAttribute("sessionattribute","56");



        return "landingpage";
    }

    @GetMapping("/firstpage")
    public String firstPage(Model model, HttpServletRequest request)
    {
        HttpSession session = request.getSession(false);
        session.getAttribute("sessionattribute");

        model.addAttribute("sessionattribute",session.getAttribute("sessionattribute"));

        //ObjectMapper objectMapper = new ObjectMapper(); //readValue, writeValue

        return "firstpage";

    }

    @PostMapping("/login")
    public String login()
    {
        return "login";
    }

    @GetMapping("/getAllCustomers")
    public Flux<Customer> getAllCustomers()
    {
        Flux<Customer> fluxCustomer = webClientBuilder.build().get().uri("http://localhost:8083/api/0.1/test-service-1/getAll").retrieve().bodyToFlux(Customer.class);
        List<Customer> listCustomer= fluxCustomer.collectList().block();
        return webClientBuilder.build().get().uri("http://localhost:8083/api/0.1/test-service-1/getAll").retrieve().bodyToFlux(Customer.class);
    }
}
