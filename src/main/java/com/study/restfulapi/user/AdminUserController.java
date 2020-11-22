package com.study.restfulapi.user;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.study.restfulapi.exception.UserNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminUserController {

    private UserDaoService service;

    public AdminUserController(UserDaoService service) {
        this.service = service;
    }

    @GetMapping("/users")
    public MappingJacksonValue retrieveAllUsers() {
        List<User> users = service.findAll();


        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                .filterOutAllExcept("id","name","joinDate","password");

        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo", filter);

        MappingJacksonValue mapping = new MappingJacksonValue(users);
        mapping.setFilters(filters);



        return mapping;
    }

    // Get /users/1 or /users/10
    // @GetMapping("/v1/users/{id}")  ====> Version control with URL
   // @GetMapping(value = "/users/{id}/", params = "version=1")  ====> Version control with URL parameter
   // @GetMapping(value = "/users/{id}", headers = "X-API-VERSION=1") ====> Version control with header
    //@GetMapping(value = "/users/{id}", produces = "application/vnd.company.appv1+json")  ====>  Version control with MIME type

    @GetMapping("/v1/users/{id}")
    public MappingJacksonValue retrieveUser(@PathVariable int id) {

        User user = service.findOne(id);

        if (user == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                .filterOutAllExcept("id","name","joinDate","password","ssn");

        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo", filter);

        MappingJacksonValue mapping = new MappingJacksonValue(user);
        mapping.setFilters(filters);

        return mapping;
    }


    // Get /users/1 or /users/10
    // @GetMapping("/v2/users/{id}") ====> Version control with URL
    // @GetMapping(value = "/users/{id}/", params = "version=2") ====> Version control with URL parameter
    // @GetMapping(value = "/users/{id}", headers = "X-API-VERSION=2") ====> Version control with header
    // @GetMapping(value = "/users/{id}", produces = "application/vnd.company.appv2+json") ====>  Version control with MIME type
    @GetMapping("/v2/users/{id}")
    public MappingJacksonValue retrieveUserv2(@PathVariable int id) {

        User user = service.findOne(id);

        if (user == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }

        UserV2 userV2 = new UserV2();
        BeanUtils.copyProperties(user, userV2);
        userV2.setGrade("VIP");

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                .filterOutAllExcept("id","name","joinDate","grade");

        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfoV2", filter);

        MappingJacksonValue mapping = new MappingJacksonValue(userV2);
        mapping.setFilters(filters);

        return mapping;
    }


}
