package com.study.restfulapi.user;

import com.study.restfulapi.exception.UserNotFoundException;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class UserController {

    private UserDaoService service;

    public UserController(UserDaoService service) {
        this.service = service;
    }

    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return service.findAll();
    }

    // Get /users/1 or /users/10
    @GetMapping("/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable int id) {

        User user = service.findOne(id);

        if (user == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }

        // HATEOAS기능 추가.
        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());
        EntityModel<User> resource = EntityModel.of(user).add(linkTo.withRel("all-users"));

        return resource;
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody  User user){
        User savedUser = service.save(user);

        //응답코드 바꾸기. uri가 헤더에 추가 된다. 이를 이용해 어떤 아이디로 사용자가 추가 되었는지 알수 있다.
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {
        User user = service.deleteById(id);

        if (user == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }

    }


}
