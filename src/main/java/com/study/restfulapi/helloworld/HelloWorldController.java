package com.study.restfulapi.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HelloWorldController {

    @Autowired
    private MessageSource messageSource;


    // GET
    // hello-world(endpoint)
    // @RequestMapping(method=RequestMethod.GET, path="/phello-world")
    @GetMapping("/hello-world")
    public String hellowWorld() {
        return "Hello World";
    }

    //This time, let's return Java bean.
    @GetMapping("/hello-world-bean")
    public HelloWorldBean helloWorldBean(){
        return new HelloWorldBean("Hello World");
    }


    //This time, let's return Java bean.
    @GetMapping("/hello-world-bean/path-var/{name}")
    public HelloWorldBean helloWorldBeanPathVar(@PathVariable String name){
        return new HelloWorldBean(String.format("Hello World, %s", name));
    }


    //다국어 설정.
    @GetMapping("/hello-world-internationalized")
    public String helloWorldInternationalized(@RequestHeader(name = "Accept-Language", required = false) Locale locale){
        return messageSource.getMessage("greeting.message", null, locale);
    }



}
