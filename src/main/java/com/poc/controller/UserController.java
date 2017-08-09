package com.poc.controller;

import com.poc.DTO.SignUpDTO;
import com.poc.DTO.UserInfoDTO;
import com.poc.model.User;
import com.poc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<UserInfoDTO> userInfo(@AuthenticationPrincipal User user) {
        return new ResponseEntity<>(new UserInfoDTO(user), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> signUp(@RequestBody SignUpDTO user) {
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
