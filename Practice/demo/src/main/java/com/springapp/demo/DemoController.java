package com.springapp.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
public class DemoController {
    private final RequestData reqData;
    private final UserSession userSess;

    public DemoController(RequestData reqData, UserSession userSess){
        this.reqData = reqData;
        this.userSess = userSess;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello Spring Boot Mani!";
    }

    @GetMapping("/request")
    public String request() {
        return reqData.getId();
    }

    @GetMapping("/user")
    public String user() {
        return userSess.getUserName();
    }

    @GetMapping("/login/{name}")
    public String login(@PathVariable String name) {
        userSess.setUserName(name);
        return "Logged in as new" + name;
    }
}
