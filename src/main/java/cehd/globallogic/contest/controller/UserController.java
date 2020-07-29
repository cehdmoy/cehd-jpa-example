package cehd.globallogic.contest.controller;

import cehd.globallogic.contest.dto.request.UserCreateRequest;
import cehd.globallogic.contest.dto.response.UserCreateResponse;
import cehd.globallogic.contest.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Validated
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping(path = "/user")
    @Validated
    @ResponseStatus(HttpStatus.CREATED)
    public UserCreateResponse createUser(@Valid @RequestBody UserCreateRequest userCreateRequest) {
        return userService.createUser(userCreateRequest);
    }

    @GetMapping(path = "user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserCreateResponse retrieveUser(@PathVariable String id) {
        return userService.retrieveUser(id);
    }

    @DeleteMapping(path = "user/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return "";
    }
}
