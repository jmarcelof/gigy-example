package com.poc.controller;

import com.poc.DTO.*;
import com.poc.model.User;
import com.poc.repository.UserRepository;
import com.poc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository users;

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<UserInfoDTO> userInfo(@AuthenticationPrincipal User user) {
        return new ResponseEntity<>(new UserInfoDTO(user), HttpStatus.OK);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<Collection<UserInfoDTO>> allUsersInfo() {
        List<UserInfoDTO> result = users.findAll().stream()
                .map(UserInfoDTO::new)
                .collect(Collectors.toList());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> signUp(@RequestBody SignUpDTO user) {
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable Long userId) {
        userService.remove(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
