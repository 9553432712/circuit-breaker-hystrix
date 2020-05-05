package com.example.consumer;


/*
Author: Mahesh Punugupati
*/

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class Controller {

    @Autowired RestTemplate restTemplate;

    @RequestMapping("/consumer/hi/{name}")
    @HystrixCommand(fallbackMethod = "error",  commandKey = "sayHellow",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000") })
    public String sayHellow(@PathVariable("name") String name) throws URISyntaxException {
        URI uri = URI.create("http://localhost:9090/producer/hi/C"+name);
        restTemplate.getForObject(uri, String.class);
        return "Hello Mr "+name;
    }

    public String error(String  e){
        return "got error"+e;
    }
}
