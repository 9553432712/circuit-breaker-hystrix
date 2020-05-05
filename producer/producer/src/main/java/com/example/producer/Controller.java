package com.example.producer;


/*
Author: Mahesh Punugupati
*/

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @RequestMapping("/producer/hi/{name}")
    public String sayHellow(@PathVariable("name") String name){
        return "Hello Mr "+name;
    }
}
