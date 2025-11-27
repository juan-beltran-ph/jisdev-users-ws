package com.jisdev.demo_ws.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jisdev.demo_ws.model.UserData;
import com.jisdev.demo_ws.model.UserResponse;
import com.jisdev.demo_ws.service.IUserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    IUserService userService;
    
    @Autowired
    private Environment env;

    @GetMapping("/status/check")
    public String getHealt(){
        log.info("HelthCheck");
        return "It's working on port " + env.getProperty("local.server.port");
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getUsers(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "limit", defaultValue = "10") int limit,
            @RequestParam(value = "sort", defaultValue = "desc", required = false) String sort) {
        log.info("getUsers called with page={}, size={}, sort={}", page, limit, sort);

        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping(path = "/{id}",produces ={ MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<UserResponse> getUser(@PathVariable("id") String id) {
        UserResponse user = userService.getUserById(id);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<UserResponse> postUser(@RequestBody @Valid UserData user) {
        UserResponse newUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> putUser(@PathVariable("id") String id, @RequestBody @Valid UserData updated) {
        UserResponse existing = userService.updateUser(id, updated);
        if (existing == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(existing);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") String id) {
        boolean removed = userService.deleteUser(id);
        if (!removed) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.noContent().build();
    }

}
